plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

android {
    boilerplate()
}

kotlin {
    js {
        browser { }
    }
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":lib-basic"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
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
