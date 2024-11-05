plugins {
    alias(libs.plugins.connect.kmp.common)
}

kmpLibrary {
    namespace = "ru.connect.domain"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":modules:core"))
        }
    }
}
