package base

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

class KmpComposeLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        applyPlugins(
            libs.findPlugin("kotlinMultiplatform").get().get().pluginId,
            libs.findPlugin("androidLibrary").get().get().pluginId,
            libs.findPlugin("serialization").get().get().pluginId,
            libs.findPlugin("ksp").get().get().pluginId,
            libs.findPlugin("composeMultiplatform").get().get().pluginId,
            libs.findPlugin("composeCompiler").get().get().pluginId,
        )

        val rootMinSdk: Int? by rootProject.extra
        val minSdkVersion: Int = rootMinSdk.takeIf { it != null && it > 0 }
            ?: project.libs.findVersion("android-minSdk").get().toString().toInt()
        log("build with minSdkVersion = $minSdkVersion")

        configureLibraryPlugin(
            compileSdkVersion = project.libs.findVersion("android-compileSdk").get().toString().toInt(),
            minSdkVersion = minSdkVersion,
        )

        val extension = extensions.create<KmpLibraryExtension>(KmpLibraryExtension.NAME)
        setupAndroidComponentsExtension(extension)

        setupKotlinMultiplatformExtension(baseName = extension.namespace)
    }

    private fun Project.setupKotlinMultiplatformExtension(baseName: String?) {

        // returns not null, if plugin block contains `jetbrains-compose`-plugin.
        // that's need for collecting compose libs
        val compose = runCatching { extensions.getByType<ComposeExtension>().dependencies }.getOrNull()

        extensions.configure<KotlinMultiplatformExtension> {
            androidTarget {
                @OptIn(ExperimentalKotlinGradlePluginApi::class)
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    this.baseName = baseName ?: project.name
                    isStatic = true
                }
            }

            jvm("desktop")

            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                browser {
                    val rootDirPath = project.rootDir.path
                    val projectDirPath = project.projectDir.path
                    commonWebpackConfig {
                        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                            static = (static ?: mutableListOf()).apply {
                                // Serve sources to debug inside browser
                                add(rootDirPath)
                                add(projectDirPath)
                            }
                        }
                    }
                }
                binaries.executable()
            }

            sourceSets.apply {
                androidMain.dependencies {
                    compose?.let {
                        implementation(compose.preview)
                    }
                    implementation(libs.findLibrary("androidx-activity-compose").get())
                }
                commonMain {
                    kotlin.srcDir("build/generated/ksp/metadata")
                }
                commonMain.dependencies {
                    compose?.let {
                        implementation(compose.runtime)
                        implementation(compose.foundation)
                        implementation(compose.material)
                        implementation(compose.material3)
                        implementation(compose.ui)
                        implementation(compose.components.resources)
                        implementation(compose.components.uiToolingPreview)
                    }
                    implementation(libs.findLibrary("androidx-lifecycle-viewmodel").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
                    implementation(libs.findLibrary("navigation-compose").get())
                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                    implementation(libs.findLibrary("kotlinx-serialization").get())
                    implementation(libs.findLibrary("kotlinx-datetime").get())
                    implementation(project.dependencies.platform(libs.findLibrary("koin-annotation-bom").get()))
                    implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))
                    implementation(libs.findBundle("koin-kmp").get())
                    implementation(libs.findLibrary("stately-concurrency").get())
                    implementation(libs.findLibrary("stately-concurrent-collections").get())
                    implementation("com.mohamedrejeb.calf:calf-file-picker:0.5.5")
                    implementation("io.coil-kt.coil3:coil-compose:3.0.0")
                    implementation("io.coil-kt.coil3:coil-network-ktor3:3.0.0")
                }
            }

            dependencies {
                compose?.let {
                    add("debugImplementation", compose.uiTooling)
                }
                add("kspCommonMainMetadata", libs.findLibrary("koin-ksp-compilier").get())
                add("kspCommonMainMetadata", project.dependencies.platform(libs.findLibrary("koin-annotation-bom").get()))
            }
        }

        // WORKAROUND: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
        tasks.withType<KotlinCompile<*>>().configureEach {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
        afterEvaluate {
            tasks.filter {
                it.name.contains("SourcesJar", true)
            }?.forEach {
                println("SourceJarTask====>${it.name}")
                it.dependsOn("kspCommonMainKotlinMetadata")
            }
        }

        extensions.configure<KspExtension> {
            arg("KOIN_CONFIG_CHECK", "true")
            arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
            arg("KOIN_DEFAULT_MODULE", "false")
        }
    }
}
