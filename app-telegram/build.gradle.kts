import java.util.*

plugins {
    kotlin("jvm")
    application
    id("com.github.kukuhyoniatmoko.buildconfigkotlin") version "1.0.5"
}

fun getLocalProperty(key: String): String {
    fun printError() {
        val message = "ERROR! Please create local.properties with key $key"
        println(message)
        System.err.println(message)
    }

    val propertiesFile: File = rootProject.file("local.properties")
    val properties = Properties()
    if (propertiesFile.exists()) {
        properties.load(propertiesFile.inputStream())
        val value: String? = properties.getProperty(key)
        if (value != null) {
            return value
        } else {
            printError()
            return "error"
        }
    } else {
        printError()
        return "error"
    }
}

buildConfigKotlin {
    sourceSet("main") {
        packageName = "com.sample"
        buildConfig(name = "TELEGRAM_TOKEN", value = getLocalProperty("TELEGRAM_TOKEN"))
    }
}

application {
    mainClassName = "com.sample.StarterKt"
    applicationName = "app"
}

dependencies {
    implementation(project(":lib-basic"))
    implementation(project(":app-di"))
    implementation(project(":lib-telegram-jvm"))
}
