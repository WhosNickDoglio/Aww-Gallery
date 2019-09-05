/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val activity_ktx: String = "androidx.activity:activity-ktx:" + Versions.activity_ktx

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val core_testing: String = "androidx.arch.core:core-testing:" + Versions.core_testing

    /**
     * http://tools.android.com
     */
    const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
        Versions.constraintlayout

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val fragment_ktx: String = "androidx.fragment:fragment-ktx:" + Versions.androidx_fragment

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val fragment_testing: String = "androidx.fragment:fragment-testing:" +
        Versions.androidx_fragment

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val lifecycle_common_java8: String = "androidx.lifecycle:lifecycle-common-java8:" +
        Versions.androidx_lifecycle

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val lifecycle_livedata_ktx: String = "androidx.lifecycle:lifecycle-livedata-ktx:" +
        Versions.androidx_lifecycle

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val lifecycle_runtime_ktx: String = "androidx.lifecycle:lifecycle-runtime-ktx:" +
        Versions.androidx_lifecycle

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val lifecycle_viewmodel_ktx: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:" +
        Versions.androidx_lifecycle

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val navigation_fragment_ktx: String = "androidx.navigation:navigation-fragment-ktx:" +
        Versions.androidx_navigation

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val navigation_safe_args_gradle_plugin: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.androidx_navigation

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val navigation_ui_ktx: String = "androidx.navigation:navigation-ui-ktx:" +
        Versions.androidx_navigation

    /**
     * http://developer.android.com/tools/extras/support-library.html
     */
    const val paging_runtime_ktx: String = "androidx.paging:paging-runtime-ktx:" +
        Versions.paging_runtime_ktx

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val recyclerview: String = "androidx.recyclerview:recyclerview:" + Versions.recyclerview

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val swiperefreshlayout: String = "androidx.swiperefreshlayout:swiperefreshlayout:" +
        Versions.swiperefreshlayout

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val work_runtime_ktx: String = "androidx.work:work-runtime-ktx:" +
        Versions.work_runtime_ktx

    /**
     * https://github.com/airbnb/epoxy
     */
    const val epoxy_paging: String = "com.airbnb.android:epoxy-paging:" +
        Versions.com_airbnb_android

    /**
     * https://github.com/airbnb/epoxy
     */
    const val epoxy_processor: String = "com.airbnb.android:epoxy-processor:" +
        Versions.com_airbnb_android

    /**
     * https://github.com/airbnb/epoxy
     */
    const val epoxy: String = "com.airbnb.android:epoxy:" + Versions.com_airbnb_android

    const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

    /**
     * https://developer.android.com/studio
     */
    const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
        Versions.com_android_tools_build_gradle

    /**
     * https://developer.android.com/studio
     */
    const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

    const val jacoco_android: String = "com.dicedmelon.gradle:jacoco-android:" +
        Versions.jacoco_android

    /**
     * http://developer.android.com/tools/extras/support-library.html
     */
    const val material: String = "com.google.android.material:material:" + Versions.material

    /**
     * https://github.com/google/dagger
     */
    const val dagger_compiler: String = "com.google.dagger:dagger-compiler:" +
        Versions.com_google_dagger

    /**
     * https://github.com/google/dagger
     */
    const val dagger: String = "com.google.dagger:dagger:" + Versions.com_google_dagger

    const val com_gradle_build_scan_gradle_plugin: String =
        "com.gradle.build-scan:com.gradle.build-scan.gradle.plugin:" +
            Versions.com_gradle_build_scan_gradle_plugin

    const val timber: String = "com.jakewharton.timber:timber:" + Versions.timber

    const val gradle_license_plugin: String = "com.jaredsburrows:gradle-license-plugin:" +
        Versions.gradle_license_plugin

    const val ktlint: String = "com.pinterest:ktlint:" + Versions.ktlint

    const val delect_plugin: String = "com.soundcloud.delect:delect-plugin:" +
        Versions.delect_plugin

    const val assisted_inject_annotations_dagger2: String =
        "com.squareup.inject:assisted-inject-annotations-dagger2:" +
            Versions.com_squareup_inject

    const val assisted_inject_processor_dagger2: String =
        "com.squareup.inject:assisted-inject-processor-dagger2:" + Versions.com_squareup_inject

    /**
     * http://github.com/square/leakcanary/
     */
    const val leakcanary_android: String = "com.squareup.leakcanary:leakcanary-android:" +
        Versions.leakcanary_android

    const val moshi_kotlin_codegen: String = "com.squareup.moshi:moshi-kotlin-codegen:" +
        Versions.com_squareup_moshi

    /**
     * https://github.com/square/moshi
     */
    const val moshi: String = "com.squareup.moshi:moshi:" + Versions.com_squareup_moshi

    /**
     * https://github.com/square/okhttp
     */
    const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
        Versions.com_squareup_okhttp3

    /**
     * https://github.com/square/okhttp
     */
    const val okhttp: String = "com.squareup.okhttp3:okhttp:" + Versions.com_squareup_okhttp3

    /**
     * http://github.com/square/retrofit/
     */
    const val converter_moshi: String = "com.squareup.retrofit2:converter-moshi:" +
        Versions.com_squareup_retrofit2

    /**
     * http://github.com/square/retrofit/
     */
    const val retrofit: String = "com.squareup.retrofit2:retrofit:" +
        Versions.com_squareup_retrofit2

    /**
     * https://github.com/square/sqldelight/
     */
    const val android_driver: String = "com.squareup.sqldelight:android-driver:" +
        Versions.com_squareup_sqldelight

    const val android_paging_extensions: String =
        "com.squareup.sqldelight:android-paging-extensions:" + Versions.com_squareup_sqldelight

    /**
     * https://github.com/square/sqldelight/
     */
    const val gradle_plugin: String = "com.squareup.sqldelight:gradle-plugin:" +
        Versions.com_squareup_sqldelight

    /**
     * https://github.com/square/sqldelight/
     */
    const val runtime_jvm: String = "com.squareup.sqldelight:runtime-jvm:" +
        Versions.com_squareup_sqldelight

    const val de_fayard_buildsrcversions_gradle_plugin: String =
        "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
            Versions.de_fayard_buildsrcversions_gradle_plugin

    const val coil_base: String = "io.coil-kt:coil-base:" + Versions.io_coil_kt

    const val coil: String = "io.coil-kt:coil:" + Versions.io_coil_kt

    const val detekt_cli: String = "io.gitlab.arturbosch.detekt:detekt-cli:" +
        Versions.io_gitlab_arturbosch_detekt

    const val io_gitlab_arturbosch_detekt_gradle_plugin: String =
        "io.gitlab.arturbosch.detekt:io.gitlab.arturbosch.detekt.gradle.plugin:" +
            Versions.io_gitlab_arturbosch_detekt

    const val junit: String = "junit:junit:" + Versions.junit

    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String =
        "org.gradle.kotlin.kotlin-dsl:org.gradle.kotlin.kotlin-dsl.gradle.plugin:" +
            Versions.org_gradle_kotlin_kotlin_dsl_gradle_plugin

    const val org_jacoco_agent: String = "org.jacoco:org.jacoco.agent:" + Versions.org_jacoco

    const val org_jacoco_ant: String = "org.jacoco:org.jacoco.ant:" + Versions.org_jacoco

    const val kotlin_android_extensions_runtime: String =
        "org.jetbrains.kotlin:kotlin-android-extensions-runtime:" +
            Versions.kotlin_android_extensions_runtime

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_android_extensions: String =
        "org.jetbrains.kotlin:kotlin-android-extensions:" + Versions.kotlin_android_extensions

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_annotation_processing_gradle: String =
        "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" +
            Versions.kotlin_annotation_processing_gradle

    const val kotlin_compiler_embeddable: String =
        "org.jetbrains.kotlin:kotlin-compiler-embeddable:" + Versions.kotlin_compiler_embeddable

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
        Versions.kotlin_gradle_plugin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_reflect: String = "org.jetbrains.kotlin:kotlin-reflect:" +
        Versions.kotlin_reflect

    const val kotlin_sam_with_receiver: String = "org.jetbrains.kotlin:kotlin-sam-with-receiver:" +
        Versions.kotlin_sam_with_receiver

    const val kotlin_scripting_compiler_embeddable: String =
        "org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:" +
            Versions.kotlin_scripting_compiler_embeddable

    const val kotlin_stdlib_jdk8: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:" +
        Versions.kotlin_stdlib_jdk8

    /**
     * https://github.com/Kotlin/kotlinx.coroutines
     */
    const val kotlinx_coroutines_android: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.org_jetbrains_kotlinx

    /**
     * https://github.com/Kotlin/kotlinx.coroutines
     */
    const val kotlinx_coroutines_core: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" +
        Versions.org_jetbrains_kotlinx
}