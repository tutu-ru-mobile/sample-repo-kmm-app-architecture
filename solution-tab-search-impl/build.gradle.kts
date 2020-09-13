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
                api(project(":solution-tab-search-api"))
                implementation(project(":lib-basic"))
                implementation(project(":solution-search-result-api"))
                implementation(project(":solution-search-form-api"))
                implementation(project(":solution-search-start-api"))
                implementation(project(":solution-tab-search-api"))
                implementation(project(":solution-buy-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
            }
        }
        val androidMain by getting {
            dependencies {
                implementationCompose()
            }
        }
        val jvmMain by getting {
            dependencies {
                api(project(":solution-tab-search-api"))//todo redundat?
            }
        }
    }
}

fixComposeWithWorkaround()
