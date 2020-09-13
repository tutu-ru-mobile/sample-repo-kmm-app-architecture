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
                api(project(":solution-settings-api"))
                implementation(project(":lib-basic"))
                implementation(project(":solution-bonus-api"))
                implementation(project(":solution-auth-api"))
                implementation(project(":solution-ab-api"))
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
