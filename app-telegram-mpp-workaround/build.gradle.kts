plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":app-di"))
                api(project(":lib-basic"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION") {
//                    isForce = true
                }
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(project(":lib-telegram-jvm"))
            }
        }
    }
}
