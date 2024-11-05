plugins {
    alias(libs.plugins.connect.kmp.compose)
}

kmpLibrary {
    namespace = "ru.connect.feature1"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":modules:core"))
            implementation(project(":modules:core-ui"))
            implementation(project(":modules:domain"))
            implementation(project(":modules:features:feature1:common"))
        }
    }
}
