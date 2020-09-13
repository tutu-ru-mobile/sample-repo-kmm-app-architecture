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
                api(project(":solution-search-result-api"))
                api(project(":entity-ticket"))
                implementation(project(":solution-search-start-api"))
                implementation(project(":solution-buy-api"))
                implementation(project(":solution-navigation-api"))
                implementation(project(":lib-basic"))
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

            }
        }

    }
}

fixComposeWithWorkaround()
