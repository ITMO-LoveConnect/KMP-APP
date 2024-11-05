package base

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

fun Project.configureLibraryPlugin(compileSdkVersion: Int, minSdkVersion: Int) {
    androidGradle {
        compileSdk = compileSdkVersion
        defaultConfig {
            minSdk = minSdkVersion
            manifestPlaceholders["screenOrientation"] = "unspecified"
        }

        buildTypes {
            named("release") {
                manifestPlaceholders["screenOrientation"] = "portrait"
            }
        }

        sourceSets {
            getByName("main").java.srcDir("src/main/kotlin")
        }
        kotlinExtension.apply {
            jvmToolchain(17)
        }
    }
}

fun Project.setupAndroidComponentsExtension(extension: KmpLibraryExtension) {
    extensions.getByType(AndroidComponentsExtension::class.java).finalizeDsl { androidComponents ->
        configureNamespace(extension.namespace)
    }
}
