

plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

apply(rootProject.file(".buildsystem/ktlint.gradle.kts"))

android {
  compileSdkVersion(App.compileSdk)
  defaultConfig {
    minSdkVersion(App.minSdk)
    targetSdkVersion(App.targetSdk)
    versionCode = App.versionCode
    versionName = App.versionName
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}
dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(Libs.kotlin_stdlib_jdk8)
  implementation(Libs.rxjava)
  implementation(Libs.rxkotlin)
  implementation(Libs.dagger)
  implementation(Libs.timber)
  implementation(Libs.paging_runtime)
  implementation(Libs.room_runtime)
  implementation(Libs.room_rxjava2)
  kapt(Libs.room_compiler)
  kapt(Libs.dagger_compiler)

  testImplementation(Libs.junit)
  testImplementation(Libs.kotlin_test_junit)
  testImplementation(Libs.mockk)
}