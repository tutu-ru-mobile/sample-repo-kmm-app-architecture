plugins {
  kotlin("jvm") version embeddedKotlinVersion
  `kotlin-dsl`
//  `embedded-kotlin`
//  `kotlin-dsl-precompiled-script-plugins`
}

repositories {
  google()
  jcenter()
}

dependencies {
  implementation(gradleApi())
  compileOnly("com.android.tools.build:gradle:4.2.0-alpha08")
  compileOnly("com.android.tools.build:gradle-api:4.2.0-alpha08")
  compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin-api:$embeddedKotlinVersion")
  compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
  compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable:$embeddedKotlinVersion")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions.jvmTarget = "1.8"
}
