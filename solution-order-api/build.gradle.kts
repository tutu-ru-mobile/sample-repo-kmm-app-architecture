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
                api(project(":entity-ticket"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementationComposeApi()
            }
        }
    }
}

fixComposeWithWorkaround()
