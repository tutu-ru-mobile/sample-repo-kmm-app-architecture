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
            addJvmSourceDirs()
            dependencies {
                implementation(project(":lib-telegram-jvm"))
                implementation("com.jakewharton.crossword:crossword:0.1.1")
            }
        }
    }
}
