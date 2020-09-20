import java.util.*

plugins {
    kotlin("jvm")
    application
}

application {
    mainClassName = "com.sample.StarterKt"
    applicationName = "app"
}

dependencies {
    implementation("com.jakewharton.crossword:crossword:0.1.1")
}
