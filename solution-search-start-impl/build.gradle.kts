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
                api(project(":solution-search-start-api"))
                implementation(project(":solution-navigation-api"))
                api(project(":solution-search-result-api"))
                api(project(":solution-order-api"))
                implementation(project(":lib-basic"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementationCompose()
                implementation(project(":solution-tab-search-api"))
            }
        }
        val jvmMain by getting {
            addJvmSourceDirs()
        }

    }
}

fixComposeWithWorkaround()
