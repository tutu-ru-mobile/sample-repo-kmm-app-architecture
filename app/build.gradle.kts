plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    boilerplate()
    buildToolsVersion(ANDROID_BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId("com.example.jetpackcomposeplayground")
        minSdkVersion(ANDROID_MIN_SDK)
        targetSdkVersion(ANDROID_TARGET_SDK)
        versionCode(1)
        versionName("0.1.1")
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
    implementationCompose()
    implementation(project(":lib-basic"))
    implementation(project(":app-di"))
    implementation(project(":solution-settings-api"))//todo remove
    implementation(project(":solution-settings-impl"))//todo remove
    implementation(project(":solution-tabs-impl"))//todo redundant?
    implementation("androidx.appcompat:appcompat:$ANDROID_APP_COMPAT")
}
