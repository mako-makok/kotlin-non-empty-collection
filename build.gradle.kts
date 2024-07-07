plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.spotless)
}

group = "org.mako-makok"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

spotless {
    kotlin {
        ktlint()
    }
}

kotlin {
    jvmToolchain(17)
    sourceSets {
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotest.assertionsCore)
                implementation(libs.kotest.property)
            }

        }
    }

    jvm()
    js(IR) {
        browser()
        nodejs()
    }

    // https://kotlinlang.org/docs/native-target-support.html#tier-1
    macosX64()
    macosArm64()
    iosSimulatorArm64()
    iosX64()

    // https://kotlinlang.org/docs/native-target-support.html#tier-2
    linuxX64()
    linuxArm64()

    watchosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()

    tvosSimulatorArm64()
    tvosX64()
    tvosArm64()

    iosArm64()

    // https://kotlinlang.org/docs/native-target-support.html#tier-3
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    mingwX64()

    watchosDeviceArm64()
}

