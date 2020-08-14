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
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":lib-basic"))
                implementation(project(":solution-order-api"))
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
