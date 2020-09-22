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
    implementation(project(":lib-basic"))
    implementation(kotlin("stdlib-jdk8"))//todo redundant?
    implementation(project(":app-console-mpp-workaround"))
}
