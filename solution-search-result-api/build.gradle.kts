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
                implementation(project(":solution-order-api"))
                implementation(project(":entity-navigation-event"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementationComposeApi()
            }
        }
        val jvmMain by getting {
            dependencies {
                api(project(":lib-telegram-adapter"))
            }
        }
    }
}

fixComposeWithWorkaround()
