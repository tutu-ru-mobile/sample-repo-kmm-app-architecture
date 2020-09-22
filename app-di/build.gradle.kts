plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

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
                implementation(project(":solution-settings-impl"))
                implementation(project(":solution-settings-api"))
                implementation(project(":solution-auth-impl"))
                implementation(project(":solution-bonus-impl"))
                implementation(project(":solution-order-impl"))
                implementation(project(":solution-search-form-impl"))
                implementation(project(":solution-search-result-impl"))
                implementation(project(":solution-search-start-impl"))
                implementation(project(":solution-tab-search-impl"))
                implementation(project(":solution-tabs-impl"))
                implementation(project(":solution-attention-impl"))
                implementation(project(":solution-weather-impl"))
                implementation(project(":solution-ab-impl"))
                implementation(project(":solution-buy-impl"))
                implementation(project(":solution-navigation-api"))
                implementation(project(":lib-basic"))
            }
        }
        val iOSMain by getting {
            dependencies {
//                implementation("io.ktor:ktor-client-ios:$KTOR_VERSION")
            }
        }
        val androidMain by getting {
            dependencies {
                implementationCompose()//todo redundant?
            }
        }
        val jvmMain by getting {
            addJvmSourceDirs()
            dependencies {
                implementation(project(":lib-telegram-jvm"))
                implementation("com.jakewharton.crossword:crossword:0.1.1")
            }
        }
    }
}

fixComposeWithWorkaround()
