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
                api(project(":solution-attention-api"))
                implementation(project(":lib-basic"))
                implementation(project(":solution-navigation-api"))
                implementation(project(":solution-auth-api"))
                implementation(project(":solution-order-api"))
                implementation(project(":solution-weather-api"))
                implementation(project(":solution-ab-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
            }
        }
        val androidMain by getting {
            dependencies {
                implementationCompose()
                implementation(project(":solution-bonus-api"))
            }
        }
    }
}

fixComposeWithWorkaround()
