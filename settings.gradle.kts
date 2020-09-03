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

rootProject.name = "solution_architecture" //todo rename
include("app-android")
include("app-di")
include("lib-basic")
include("entity-ticket")
include("solution-auth-api")
include("solution-auth-impl")

include("solution-bonus-api")
//project(":solution-bonus-api").projectDir = file("sample_kmpp_solution_bonus_api")
include("solution-bonus-impl")
project(":solution-bonus-impl").projectDir = file("solution-bonus-impl/solution")
include("solution-bonus-impl-sample-android")
project(":solution-bonus-impl-sample-android").projectDir = file("solution-bonus-impl/sample-android")

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

