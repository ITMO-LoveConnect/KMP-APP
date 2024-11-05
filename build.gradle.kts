
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.connect.kmp.common) apply false
    alias(libs.plugins.connect.kmp.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt.plugin)
}

detekt {
    source = files(projectDir)
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = false
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    exclude("**/.idea/**", "**/build/**", "**/.gradle-cache/**", "modules/core/**")
}

val detektAutoCorrect by tasks.registering(io.gitlab.arturbosch.detekt.Detekt::class) {
    setSource(files(projectDir))
    config.from(files("config/detekt/detekt.yml"))
    autoCorrect = true
}

task<Copy>("installDetektGitHook") {
    from("${rootProject.rootDir}/scripts/pre-commit")
    into("${rootProject.rootDir}/.git/hooks")
    fileMode = 0b111101101
}

task<Delete>("deleteDetektGitHook") {
    delete("${rootProject.rootDir}/.git/hooks/pre-commit")
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}
