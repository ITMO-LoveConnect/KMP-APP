plugins {
    alias(libs.plugins.connect.kmp.compose)
}

kmpLibrary {
    namespace = "ru.connect.profile"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":modules:core"))
            implementation(project(":modules:core-ui"))
            implementation(project(":modules:core:navigation"))
            implementation(project(":modules:features:profile:common"))
            implementation(project(":modules:domain"))
        }
    }
}
