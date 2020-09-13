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
                implementation(project(":solution-tabs-api"))
                implementation(project(":solution-tabs-impl"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(project(":lib-telegram-jvm"))
            }
        }
    }
}
