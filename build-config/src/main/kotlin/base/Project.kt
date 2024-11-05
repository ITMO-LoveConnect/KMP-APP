package base

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

/**
 * Provides access to the deps declared in the version catalog
 */
val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal inline fun Project.androidGradle(crossinline configure: LibraryExtension.() -> Unit) =
    extensions.configure<LibraryExtension> {
        configure()
    }

internal fun Project.applyPlugins(vararg pluginIds: String) {
    pluginIds.filterNot(plugins::hasPlugin).forEach(plugins::apply)
}

internal fun Project.configureNamespace(libraryNamespace: String?) {
    if (libraryNamespace == null) return
    androidGradle {
        namespace = libraryNamespace
    }
}

// gradlew aGoogleDebug -PbuildLogs
internal fun Project.log(message: String) {
    if (!hasProperty("buildLogs")) return
    println(message)
}
