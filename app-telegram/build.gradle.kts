plugins {
    kotlin("jvm")
    application
}

application {
    mainClassName = "com.sample.StarterKt"
    applicationName = "app"
}

dependencies {
    implementation(project(":lib-basic"))
}
