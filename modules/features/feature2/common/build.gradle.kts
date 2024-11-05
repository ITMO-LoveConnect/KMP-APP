plugins {
    alias(libs.plugins.connect.kmp.compose)
}

kmpLibrary {
    namespace = "ru.connect.feature2.common"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":modules:core"))
            api(project(":modules:core:navigation"))
        }
    }
}
