pluginManagement {
    repositories {
        jcenter()
        google()
        gradlePluginPortal()
    }

    plugins {
        id("com.android.application") version "4.0.1"
        id("com.android.library") version "4.0.1"
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application", "com.android.library" -> {
                    useModule(
                        if (System.getProperty("buildJetPackCompose") == "true") {
                            "com.android.tools.build:gradle:4.2.0-alpha10"
                        } else {
//                            "com.android.tools.build:gradle:4.0.1"
                            "com.android.tools.build:gradle:4.1.0-rc02"
                        }
                    )
                }
            }
        }
    }
}
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

rootProject.name = "kmm_app"
include("app-telegram")
include("lib-telegram-jvm")
include("app-android")
include("app-di")
include("lib-basic")
include("entity-ticket")
include("entity-navigation-event")
include("solution-auth-api")
include("solution-auth-impl")

include("solution-bonus-api")
include("solution-bonus-impl")
//include("solution-bonus-impl-sample-android")
//project(":solution-bonus-impl-sample-android").projectDir = file("solution-bonus-impl/sample-android")

include("solution-order-api")
include("solution-order-impl")
include("solution-search-form-api")
include("solution-search-form-impl")
include("solution-search-result-api")
include("solution-search-result-impl")
include("solution-search-start-api")
include("solution-search-start-impl")
include("solution-settings-api")
include("solution-settings-impl")
include("solution-tab-search-api")
include("solution-tab-search-impl")
include("solution-tabs-api")
include("solution-tabs-impl")
include("solution-navigation-api")
include("solution-attention-api")
include("solution-attention-impl")
include("solution-weather-api")
include("solution-weather-impl")
include("solution-ab-api")
include("solution-ab-impl")
include("solution-buy-api")
include("solution-buy-impl")
include("ios-kotlin-pod")

