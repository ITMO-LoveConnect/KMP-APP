plugins {
    alias(libs.plugins.connect.kmp.compose)
}

kmpLibrary {
    namespace = "ru.connect.core.navigation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":modules:core"))
        }
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK", "false")
}
