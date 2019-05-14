/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val appcompat: String = "1.0.2"

    const val constraintlayout: String = "1.1.3"

    const val core_ktx: String = "1.0.2"

    const val fragment_ktx: String = "1.0.0"

    const val legacy_support_v4: String = "1.0.0"

    const val androidx_lifecycle: String = "2.0.0"

    const val androidx_navigation: String = "2.0.0"

    const val recyclerview: String = "1.0.0"

    const val com_android_tools_build_gradle: String = "3.4.0"

    const val lint_gradle: String = "26.4.0"

    const val com_github_bumptech_glide: String = "4.9.0"

    const val exoplayer: String = "2.10.0"

    const val com_google_dagger: String = "2.22.1"

    const val google_services: String = "4.2.0"

    const val truth: String = "0.44"

    const val retrofit2_kotlin_coroutines_adapter: String = "0.9.2"

    const val timber: String = "4.7.1"

    const val com_squareup_moshi: String = "1.8.0"

    const val com_squareup_okhttp3: String = "3.14.1"

    const val com_squareup_retrofit2: String = "2.5.0"

    const val com_squareup_sqldelight: String = "1.1.3"

    const val com_vanniktech_android_junit_jacoco_gradle_plugin: String = "0.14.0"

    const val gradle_android_junit_jacoco_plugin: String = "0.14.0"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2"

    const val io_gitlab_arturbosch_detekt: String = "1.0.0-RC14"

    const val mockk: String = "1.9.3"

    const val flick: String = "1.4.0"

    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String = "1.2.6"

    const val kotlin_android_extensions_runtime: String = "1.3.30"

    const val kotlin_android_extensions: String = "1.3.30"

    const val kotlin_annotation_processing_gradle: String = "1.3.30"

    const val kotlin_gradle_plugin: String = "1.3.30"

    const val kotlin_reflect: String = "1.3.30"

    const val kotlin_sam_with_receiver: String = "1.3.30"

    const val kotlin_scripting_compiler_embeddable: String = "1.3.30"

    const val kotlin_stdlib_jdk8: String = "1.3.30"

    const val org_jetbrains_kotlinx: String = "1.2.1"

    const val junit_jupiter: String = "5.4.2"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.4.1"

        const val currentVersion: String = "5.4.1"

        const val nightlyVersion: String = "5.5-20190508000038+0000"

        const val releaseCandidate: String = ""
    }
}
