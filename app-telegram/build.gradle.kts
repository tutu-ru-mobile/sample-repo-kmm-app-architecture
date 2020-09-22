
plugins {
    kotlin("jvm")
    application
    id("com.github.kukuhyoniatmoko.buildconfigkotlin") version "1.0.5"
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
    implementation(project(":apps-jvm-mpp-workaround"))
    implementation(project(":lib-telegram-adapter"))
}
