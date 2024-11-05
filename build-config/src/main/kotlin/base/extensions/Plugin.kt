// package base.extensions
//
// import base.Dimension
// import base.androidGradle
// import base.configureNamespace
// import base.coreLibraryDesugaring
// import base.kotlinOptions
// import base.lintChecks
// import base.log
// import base.storeDimensionFlavor
// import com.android.build.api.dsl.CommonExtension
// import com.android.build.api.dsl.LibraryBuildType
// import com.android.build.api.dsl.LibraryProductFlavor
// import com.android.build.api.dsl.Packaging
// import com.android.build.api.variant.AndroidComponentsExtension
// import com.android.build.gradle.LibraryExtension
// import org.gradle.api.JavaVersion
// import org.gradle.api.NamedDomainObjectContainer
// import org.gradle.api.Project
// import org.gradle.kotlin.dsl.configure
// import org.gradle.kotlin.dsl.dependencies
// import kotlin.collections.joinToString
// import kotlin.collections.listOf
// import kotlin.collections.plus
// import kotlin.collections.set
//
// fun Project.configureLibraryPlugin(compileSdkVersion: Int, minSdkVersion: Int, setupKotlinOptions: Boolean = true) {
//    val missingDimensionFlavor = storeDimensionFlavor
//    log("missingDimensionFlavor = $missingDimensionFlavor")
//    androidGradle {
//        compileSdk = compileSdkVersion
//        defaultConfig {
//            minSdk = minSdkVersion
//            manifestPlaceholders["screenOrientation"] = "unspecified"
//            missingDimensionStrategy(Dimension.STORE.name, missingDimensionFlavor.alias)
//        }
//
//        buildTypes {
//            named("release") {
//                manifestPlaceholders["screenOrientation"] = "portrait"
//            }
//        }
//
//        sourceSets {
//            getByName("main").java.srcDir("src/main/kotlin")
//        }
//
//        if (setupKotlinOptions) {
//            kotlinOptions {
//                freeCompilerArgs = freeCompilerArgs + "-opt-in=$OptIn"
//            }
//        }
//    }
// }
//
// fun Project.configurePackagingOptions(optionsInitializer: (Packaging.() -> Unit)?) =
//    extensions.configure<LibraryExtension> {
//        packaging {
//            optionsInitializer?.invoke(this)
//        }
//    }
//
// fun Project.configureBuildTypes(buildTypesInitializer: (NamedDomainObjectContainer<LibraryBuildType>.() -> Unit)?) =
//    extensions.configure<LibraryExtension> {
//        buildTypes {
//            buildTypesInitializer?.invoke(this)
//        }
//    }
//
// fun Project.configureProductFlavors(productFlavorsInitializer: (NamedDomainObjectContainer<LibraryProductFlavor>.() -> Unit)?) =
//    extensions.configure<LibraryExtension> {
//        productFlavors {
//            productFlavorsInitializer?.invoke(this)
//        }
//    }
//
// fun CommonExtension<*, *, *, *, *>.setupCompileOptions(enableDesugaring: Boolean = true) =
//    compileOptions {
//        isCoreLibraryDesugaringEnabled = enableDesugaring
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }
//
// fun Project.logExtensionData(extension: AndroidLibraryExtension) {
//    log("namespace = ${extension.namespace}")
//    log("enableDesugaring = ${extension.enableDesugaring}")
//    log("useViewBinding = ${extension.useViewBinding}")
//    log("useCompose = ${extension.useCompose}")
// }
//
//
//
// fun Project.setupAndroidComponentsExtension(extension: AndroidLibraryExtension) {
//    extensions.getByType(AndroidComponentsExtension::class.java).finalizeDsl { androidComponents ->
//        logExtensionData(extension)
//
//        configureNamespace(extension.namespace)
//
//        androidComponents.apply {
//            buildFeatures {
//                dataBinding {
//                    isEnabled = extension.useDataBinding
//                }
//                viewBinding = extension.useViewBinding
//                compose = extension.useCompose
//                resourcePrefix = extension.resourcePrefix
//                buildConfig = extension.useBuildConfig
//            }
//
//            lint {
//                abortOnError = extension.abortLintOnError
//            }
//
//            setupCompileOptions(extension.enableDesugaring)
//        }
//
//        configurePackagingOptions(extension.packagingOptions)
//        configureBuildTypes(extension.buildTypesConfig)
//        configureProductFlavors(extension.productFlavorsConfig)
//
//        dependencies {
//            if (extension.enableDesugaring) coreLibraryDesugaring()
//            lintChecks()
//        }
//    }
//
// }
//
// val OptIn = listOf(
//    "kotlin.RequiresOptIn",
//    "kotlinx.coroutines.ExperimentalCoroutinesApi",
//    "kotlinx.coroutines.FlowPreview",
//    "kotlin.Experimental",
//    "androidx.compose.foundation.ExperimentalFoundationApi"
// ).joinToString(separator = ",")
