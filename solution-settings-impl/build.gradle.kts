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
                api(project(":solution-settings-api"))
                implementation(project(":lib-basic"))
                implementation(project(":solution-wallet-api"))
                implementation(project(":solution-auth-api"))
                implementation(project(":solution-ab-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
            }
        }
        val androidMain by getting {
            dependencies {
                implementationCompose()
            }
        }
    }
}

fixComposeWithWorkaround()
