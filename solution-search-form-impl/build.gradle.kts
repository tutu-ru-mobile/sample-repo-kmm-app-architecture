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
                api(project(":solution-search-form-api"))
                implementation(project(":lib-basic"))
                implementation(project(":solution-attention-api"))
                implementation(project(":solution-navigation-api"))
                implementation(project(":solution-auth-api"))
                implementation(project(":solution-order-api"))
                implementation(project(":solution-search-start-api"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementationCompose()
                implementation(project(":solution-bonus-api"))
                implementation(project(":solution-tab-search-api"))
            }
        }
        val jvmMain by getting {
            dependencies {

            }
        }
    }
}

fixComposeWithWorkaround()
