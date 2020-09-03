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
        applicationId("com.sample.solution_bonus")
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
    implementation(project(":solution-bonus-impl"))
    api(project(":solution-ab-api"))
    implementation("androidx.appcompat:appcompat:$ANDROID_APP_COMPAT")
}
