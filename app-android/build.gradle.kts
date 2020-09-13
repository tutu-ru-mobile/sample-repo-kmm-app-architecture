plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    boilerplate(true)
    if (false) {
        kotlinOptions {
            jvmTarget = "1.8"
            useIR = true
            freeCompilerArgs += listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
        }
    }
    buildToolsVersion(ANDROID_BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId("com.sample.app")
        minSdkVersion(ANDROID_MIN_SDK)
        targetSdkVersion(ANDROID_TARGET_SDK)
        versionCode(1)
        versionName("0.1.1")
    }
}

dependencies {
    implementationCompose()
    implementation(project(":lib-basic"))
    implementation(project(":app-di"))
    implementation(project(":solution-tabs-impl"))
    implementation(project(":solution-search-form-api"))
    implementation(project(":solution-navigation-api"))
    implementation(project(":solution-search-form-impl"))
    implementation("androidx.appcompat:appcompat:$ANDROID_APP_COMPAT")
}
