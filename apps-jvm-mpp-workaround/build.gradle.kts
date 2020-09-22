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
            kotlin.srcDir("src/telegramMain/kotlin")
            kotlin.srcDir("src/ideaMain/kotlin")
            kotlin.srcDir("src/consoleMain/kotlin")
            println("kotlin.srcDirs: ${kotlin.srcDirs}")
            dependencies {
                implementation(project(":lib-telegram-jvm"))
                implementation("com.jakewharton.crossword:crossword:0.1.1")
            }
        }
    }
}
