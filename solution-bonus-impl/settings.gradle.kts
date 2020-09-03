//pluginManagement {
//    repositories {
//        mavenLocal()
//        gradlePluginPortal()
//        jcenter()
//        mavenCentral()
//        maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
//        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
//        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
//    }
//
////  resolutionStrategy {
////    eachPlugin {
////      when (requested.id.id) {
//////        "kotlin-dce-js" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
//////        "kotlinx-serialization" -> useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
//////        "org.jetbrains.kotlin.multiplatform" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${target.version}")
////      }
////    }
////  }
//}


include("solution-bonus-impl")
project(":solution-bonus-impl").projectDir = file("solution")
include("solution-bonus-impl-sample-android")
project(":solution-bonus-impl-sample-android").projectDir = file("sample-android")

include("solution-bonus-api")
include("solution-ab-api")
include("lib-basic")
