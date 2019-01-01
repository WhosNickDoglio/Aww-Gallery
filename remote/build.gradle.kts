
plugins {
  kotlin("jvm")
  kotlin("kapt")
}

apply(rootProject.file(".buildsystem/ktlint.gradle.kts"))

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(Libs.kotlin_stdlib_jdk8)

  implementation(Libs.retrofit)
  implementation(Libs.retrofit_mock)
  implementation(Libs.adapter_rxjava2)
  implementation(Libs.converter_moshi)
  implementation(Libs.okhttp)
  implementation(Libs.dagger)
  implementation(Libs.logging_interceptor)
  implementation(Libs.moshi_kotlin)
  kapt(Libs.moshi_kotlin_codegen)
  kapt(Libs.dagger_compiler)

  testImplementation(Libs.junit)
  testImplementation(Libs.kotlin_test_junit)
  testImplementation(Libs.mockk)
}
