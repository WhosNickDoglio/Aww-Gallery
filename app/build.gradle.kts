/*
 * MIT License
 *
 *   Copyright (c) 2020. Nicholas Doglio
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    javacOptions {
        option("-source", "8")
        option("-target", "8")
        arguments {
            arg("dagger.experimentalDaggerErrorMessages", "enabled")
        }
    }
}

tasks.withType<Test> {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = setOf(
            TestLogEvent.SKIPPED,
            TestLogEvent.PASSED,
            TestLogEvent.FAILED
        )
        showStandardStreams = true
    }
}

ktlint {
    version.set(Versions.ktlint)
    android.set(true)
    outputColorName.set("RED")
    disabledRules.set(setOf("import-ordering"))
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    defaultConfig {
        applicationId = "com.nicholasdoglio.eyebleach"
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)

    implementation("androidx.compose.ui:ui:1.0.0-alpha06")
    implementation("androidx.compose.animation:animation:1.0.0-alpha06")
    implementation("androidx.compose.material:material:1.0.0-alpha06")
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha06")
    implementation("androidx.compose.compiler:compiler:1.0.0-alpha06")
    implementation("androidx.compose.runtime:runtime:1.0.0-alpha06")
    implementation("androidx.ui:ui-tooling:1.0.0-alpha06")
    implementation("androidx.ui:ui-test:1.0.0-alpha06")

//    implementation("dev.chrisbanes.accompanist:accompanist-insets:0.3.2")
    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.3.2")

    implementation(Libs.appcompat)
    implementation(Libs.material)

    implementation("androidx.paging:paging-compose:1.0.0-alpha01")

    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshiConverter)

    implementation(Libs.okhttp)
    implementation(Libs.okhttpLogging)

    implementation(Libs.moshi)
    kapt(Libs.moshiCodeGen)

    debugImplementation(Libs.leakcanary_android)

    implementation(Libs.dagger)
    kapt(Libs.dagger_compiler)

    implementation(Libs.timber)

    testImplementation(Libs.junit)
    testImplementation(Libs.truth)
}
