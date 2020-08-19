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
                api(project(":solution-search-form-api"))
                implementation(project(":lib-basic"))
                implementation(project(":solution-attention-api"))
                implementation(project(":solution-navigation-api"))
                implementation(project(":solution-auth-api"))
                implementation(project(":solution-order-api"))
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
