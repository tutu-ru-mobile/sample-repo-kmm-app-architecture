import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

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
    cocoapods() {
        ios.deploymentTarget = "13.0"
        ios.name//read only
        summary = "solution-bonus-app-di module"
        homepage = "homepage placeholder"
    }

    js {
        browser { }
    }
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":lib-basic"))
                implementation(project(":solution-bonus-impl"))
                api(project(":solution-ab-api"))
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
