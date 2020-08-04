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
                api(project(":solution-search-start-api"))
                implementation(project(":solution-navigation-api"))
                implementation(project(":solution-order-api"))
                implementation(project(":lib-basic"))
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
