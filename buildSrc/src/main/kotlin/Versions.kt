/*
 * MIT License
 *
 * Copyright (c) 2019 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val appcompat: String = "1.0.2" 

    const val constraintlayout: String = "1.1.3" 

    const val core_ktx: String = "1.0.1"

    const val fragment_ktx: String = "1.0.0"

    const val legacy_support_v4: String = "1.0.0"

    const val androidx_lifecycle: String = "2.0.0"

    const val androidx_navigation: String = "2.0.0"

    const val paging_runtime: String = "2.1.0" 

    const val recyclerview: String = "1.0.0" 

    const val androidx_room: String = "2.0.0"

    const val espresso_core: String = "3.1.1"

    const val androidx_test: String = "1.1.1"

    const val com_airbnb_android: String = "3.3.1"

    const val aapt2: String = "3.3.2-5309881"

    const val com_android_tools_build_gradle: String = "3.3.2"

    const val lint_gradle: String = "26.3.2"

    const val com_github_bumptech_glide: String = "4.9.0"

    const val exoplayer: String = "2.9.6"

    const val com_google_dagger: String = "2.22.1" 

    const val firebase_crash: String = "16.2.1"

    const val google_services: String = "4.2.0"

    const val retrofit2_kotlin_coroutines_adapter: String = "0.9.2"

    const val timber: String = "4.7.1" 

    const val com_squareup_leakcanary: String = "1.6.3"

    const val com_squareup_moshi: String = "1.8.0"

    const val com_squareup_okhttp3: String = "3.14.0" 

    const val com_squareup_retrofit2: String = "2.5.0" 

    const val com_vanniktech_android_junit_jacoco_gradle_plugin: String = "0.13.0"

    const val gradle_android_junit_jacoco_plugin: String = "0.13.0"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2"

    const val android_junit5: String = "1.4.0.0"

    const val io_gitlab_arturbosch_detekt: String = "1.0.0-RC14"

    const val mockk: String = "1.9.3"

    const val junit: String = "4.12"

    const val flick: String = "1.4.0"

    const val assertj_core: String = "3.12.2"

    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String = "1.2.5" // available: "1.2.6"

    const val org_jacoco: String = "0.8.3"

    const val org_jetbrains_kotlin: String = "1.3.21"

    const val org_jetbrains_kotlinx: String = "1.1.1"

    const val kotlinter_gradle: String = "1.23.0"

    const val junit_jupiter: String = "5.4.1" 

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.3.1"

        const val currentVersion: String = "5.3.1"

        const val nightlyVersion: String = "5.5-20190405000045+0000"

        const val releaseCandidate: String = ""
    }
}
