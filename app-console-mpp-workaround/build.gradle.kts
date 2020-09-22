plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm(/*"commonConsole"*/)
    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
//        val commonConsoleMain by getting {
//            dependsOn(commonMain)
//            dependencies {
//
//            }
//        }
    }
}
