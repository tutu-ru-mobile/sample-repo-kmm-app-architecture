plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

android {
    boilerplate()
}

kotlin {
    iosX64("iOS")
    cocoapods {
        ios.deploymentTarget = "13.0"
        summary = "app-di module"
        homepage = "homepage placeholder"
//        pod("app_di_swift", podspec = rootProject.file("app-di/swift/app_di_swift.podspec"))
    }

    js {
        browser { }
    }
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":solution-settings-impl"))
                implementation(project(":solution-settings-api"))
                implementation(project(":solution-auth-impl"))
                implementation(project(":solution-wallet-impl"))
                implementation(project(":solution-order-impl"))
                implementation(project(":solution-search-form-impl"))
                implementation(project(":solution-search-result-impl"))
                implementation(project(":solution-search-start-impl"))
                implementation(project(":solution-tab-search-impl"))
                implementation(project(":solution-tabs-impl"))
                implementation(project(":solution-attention-impl"))
                implementation(project(":solution-weather-impl"))
                implementation(project(":solution-ab-impl"))
                implementation(project(":solution-navigation-api"))
                implementation(project(":lib-basic"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION") {
//                    isForce = true
                }
//                implementation("io.ktor:ktor-client-core:$KTOR_VERSION")
            }
        }
        val iOSMain by getting {
            dependencies {
//                implementation("io.ktor:ktor-client-ios:$KTOR_VERSION")
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
