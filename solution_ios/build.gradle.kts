plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

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
        // Configure fields required by CocoaPods.
        summary = "BikeShare common module"
        homepage = "homepage placeholder"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}") {
//                    isForce = true
//                }

                // Ktor
//                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
//                implementation("io.ktor:ktor-client-json:${Versions.ktor}")
//                implementation("io.ktor:ktor-client-logging:${Versions.ktor}")
//                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")

                // Serialize
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerialization}")
            }
        }

        val iOSMain by getting {
            dependencies {
//                implementation("io.ktor:ktor-client-ios:${Versions.ktor}")
            }
        }

    }
}
