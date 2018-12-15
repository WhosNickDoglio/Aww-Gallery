import com.nicholasdoglio.buildsrc.App
import com.nicholasdoglio.buildsrc.Libs

plugins {
  id("com.android.application")
  id("jacoco")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
}

apply(rootProject.file(".buildsystem/ktlint.gradle.kts"))

android {
  compileSdkVersion(App.compileSdk)
  defaultConfig {
    applicationId = "com.nicholasdoglio.eyebleach"
    minSdkVersion(App.minSdk)
    targetSdkVersion(App.targetSdk)
    versionCode = App.versionCode
    versionName = App.versionName
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildTypes {
      getByName("release") {
        isMinifyEnabled = false
        proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
      }
      getByName("debug") {
        isTestCoverageEnabled = true
      }
    }
    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
    }
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(project(":domain"))
  implementation(project(":data"))

  //Libraries
  implementation(Libs.org_jetbrains_kotlin_kotlin_stdlib_jdk8)
  implementation(Libs.paging_runtime)
  implementation(Libs.rxjava2)
  implementation(Libs.rxjava)
  implementation(Libs.rxrelay)
  implementation(Libs.rxkotlin)
  implementation(Libs.appcompat)
  implementation(Libs.recyclerview)
  implementation(Libs.constraintlayout)
  implementation(Libs.lifecycle_extensions)
  implementation(Libs.lifecycle_common_java8)
  implementation(Libs.core_ktx)
  implementation(Libs.fragment_ktx)
  implementation(Libs.rxandroid)
  implementation(Libs.autodispose_ktx)
  implementation(Libs.autodispose_android_ktx)
  implementation(Libs.autodispose_android_archcomponents_ktx)
  implementation(Libs.dagger)
  implementation(Libs.dagger_android)
  implementation(Libs.dagger_android_support)
  implementation(Libs.glide)
  implementation(Libs.epoxy)
  implementation(Libs.exoplayer)
  implementation(Libs.recyclerview_integration) {
    isTransitive = false
  }


  kapt(Libs.com_github_bumptech_glide_compiler)
  kapt(Libs.epoxy_processor)
  kapt(Libs.dagger_compiler)
  kapt(Libs.dagger_android_processor)

  //Debugging
  implementation(Libs.firebase_crash)
  implementation(Libs.timber)
  debugImplementation(Libs.leakcanary_android)
  debugImplementation(Libs.leakcanary_support_fragment)
  releaseImplementation(Libs.leakcanary_android_no_op)

  //Testing
  testImplementation(Libs.junit)
  testImplementation(Libs.kotlin_test_junit)
  testImplementation(Libs.mockk)
  androidTestImplementation(Libs.androidx_test_rules)
  androidTestImplementation(Libs.androidx_test_runner)
  androidTestImplementation(Libs.espresso_core)
}

// Ensure the no-op dependency is always used in JVM tests.
//configurations.all { config ->
//    if (config.name.contains("UnitTest")) {
//        config.resolutionStrategy.eachDependency { details ->
//            if (details.requested.group == "com.squareup.leakcanary" && details.requested.name == "leakcanary-android") {
//                details.useTarget(group: details.requested.group, name: "leakcanary-android-no-op", version: details.requested.version)
//            }
//        }
//    }
//}

//  apply plugin: "com.google.gms.google-services"