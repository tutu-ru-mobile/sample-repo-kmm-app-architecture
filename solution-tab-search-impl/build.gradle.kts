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
                api(project(":solution-tab-search-api"))
                implementation(project(":lib-basic"))
                implementation(project(":solution-search-result-api"))
                implementation(project(":solution-search-form-api"))
                implementation(project(":solution-search-start-api"))
                implementation(project(":solution-tab-search-api"))
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
