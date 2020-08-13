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

    targets {
        val sdkName: String? = System.getenv("SDK_NAME")

        val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
        if (isiOSDevice) {
            iosArm64("iOS64")
        } else {
            iosX64("iOS")
        }

        macosX64("macOS")
    }

    cocoapods {
        summary = "app-di module"
        homepage = "homepage placeholder"
    }

    js {
        browser { }
    }
    android()
    sourceSets {

        val iOSMain by getting {
            dependencies {
//                implementation("io.ktor:ktor-client-ios:${Versions.ktor}")
            }
        }

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
