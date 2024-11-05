plugins {
    alias(libs.plugins.connect.kmp.compose)
}

kmpLibrary {
    namespace = "ru.connect.core.ui"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":modules:core"))
        }
    }
}
