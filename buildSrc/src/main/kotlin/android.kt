import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion

inline fun BaseExtension.boilerplate() {
    compileSdkVersion(ANDROID_COMPILE_SDK)
    defaultConfig {
        minSdkVersion(ANDROID_MIN_SDK)
        targetSdkVersion(ANDROID_TARGET_SDK)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerVersion = ANDROID_KOTLIN_COMPILER_VERSION
        kotlinCompilerExtensionVersion = ANDROID_COMPOSE_VERSION
    }
}

