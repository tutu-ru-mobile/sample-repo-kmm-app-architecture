plugins {
    id("org.jetbrains.kotlin.multiplatform") version KOTLIN_VERSION apply false
//    kotlin("multiplatform") version "1.4.0" apply false
    id("com.gradleup.auto.manifest") version "1.0.2"
    id("com.android.application") apply false
//    id("com.android.library") apply false
}

autoManifest {
    packageName.set("com.sample")
    replaceDashesWithDot.set(true)
}

allprojects {
    repositories {
        //mavenLocal()
        google()
        jcenter()
        maven {
            setUrl("https://dl.bintray.com/kotlin/kotlinx")
        }
//        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
//        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    }
    tasks.withType(AbstractTestTask::class) {
        testLogging {
            showStandardStreams = true
            events("passed", "failed")
        }
    }
    tasks.withType < org.jetbrains.kotlin.gradle.tasks.KotlinCompile > {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += listOf("-Xallow-jvm-ir-dependencies")
    }

}
