plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

group = "ru.connect.buildconfig"

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins.register("ru.connect.kmpCommon") {
        id = "ru.connect.kmpCommon"
        implementationClass = "base.KmpCommonLibraryPlugin"
    }
    plugins.register("ru.connect.kmpCompose") {
        id = "ru.connect.kmpCompose"
        implementationClass = "base.KmpComposeLibraryPlugin"
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.stdlib)
    compileOnly(libs.org.jetbrains.compose.gradle.plugin)
    gradleApi()
    compileOnly(libs.ksp.gradlePlugin)
}
