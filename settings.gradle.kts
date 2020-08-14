pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    }

//  resolutionStrategy {
//    eachPlugin {
//      when (requested.id.id) {
////        "kotlin-dce-js" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
////        "kotlinx-serialization" -> useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
////        "org.jetbrains.kotlin.multiplatform" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${target.version}")
//      }
//    }
//  }
}

rootProject.name = "solution_architecture"
include("app")
include("app-di")
include("lib-basic")
include("solution-auth-api")
include("solution-auth-impl")
include("solution-wallet-api")
include("solution-wallet-impl")
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

