plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

android {
    boilerplate()
}

kotlin {
    iosX64("iOS")

    js {
        browser { }
    }
    android()
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":lib-basic"))
                api(project(":solution-navigation-api"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementationComposeApi()
            }
        }
        val jvmMain by getting {
            addJvmSourceDirs()
        }
    }
}

fixComposeWithWorkaround()
