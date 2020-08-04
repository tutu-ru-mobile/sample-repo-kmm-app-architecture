plugins {
    kotlin("multiplatform") version KOTLIN_VERSION apply false
    id("com.gradleup.auto.manifest") version AUTO_MANIFEST_VERSION
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$ANDROID_GRADLE_PLUGIN")
    }
}

autoManifest {
    packageName.set("com.sample")
    replaceDashesWithDot.set(true)
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven {
            setUrl("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
    tasks.withType(AbstractTestTask::class) {
        testLogging {
            showStandardStreams = true
            events("passed", "failed")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
    }
}
