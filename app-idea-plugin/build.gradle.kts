buildscript {// workaround https://github.com/JetBrains/gradle-intellij-plugin/issues/537
  repositories {
    maven("https://jetbrains.bintray.com/intellij-plugin-service")
  }
}

plugins {
  java
  kotlin("jvm")
  id("org.jetbrains.intellij") version "0.4.26" //https://github.com/JetBrains/gradle-intellij-plugin
  idea
  id("com.github.kukuhyoniatmoko.buildconfigkotlin") version "1.0.5"
  kotlin("plugin.serialization")
}

buildConfigKotlin {
  sourceSet("main") {
    packageName = "com.sample"
    buildConfig(name = "GITHUB_CLIENT_SECRET", value = getLocalProperty("GITHUB_CLIENT_SECRET"))
    buildConfig(name = "GITHUB_CLIENT_ID", value = getLocalProperty("GITHUB_CLIENT_ID"))
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.sample.plugin"
version = "1.0.0"

repositories {
  mavenCentral()
  maven("https://www.jetbrains.com/intellij-repository/snapshots")
  maven("https://jetbrains.bintray.com/intellij-plugin-service")
  maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("ch.qos.logback:logback-classic:1.2.3")
  implementation("io.ktor:ktor-server-netty:$KTOR_VERSION")
  implementation("io.ktor:ktor-server-cio:$KTOR_VERSION")
  implementation("io.ktor:ktor-client-core:$KTOR_VERSION")
  implementation("io.ktor:ktor-client-apache:$KTOR_VERSION")
  implementation("org.jgroups:jgroups:4.0.0.Final")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:$COROUTINES_VERSION")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$SERIALIZATION_VERSION")
  implementation(project(":lib-basic"))
  implementation(project(":lib-idea-adapter"))
  implementation(project(":apps-jvm-mpp-workaround"))
}

intellij {
  version = "2020.2.2"
  type = "IC"
  pluginName = "solution-architecture-sample"
  updateSinceUntilBuild = false
  sameSinceUntilBuild = true
  downloadSources = false
  instrumentCode = true
  sandboxDirectory = "/tmp/idea_sandbox"
}

tasks.withType<org.jetbrains.intellij.tasks.PatchPluginXmlTask>() {
  pluginDescription
}

tasks.withType<org.jetbrains.intellij.tasks.RunIdeTask> {
  systemProperties["ide.browser.jcef.enabled"] = true
  jvmArgs("-Xmx1000m", "-Xms128m")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
  kotlinOptions.freeCompilerArgs += listOf("-Xjvm-default=enable")
}
