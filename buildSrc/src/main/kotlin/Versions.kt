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
    const val rxjava2: String = "1.0.1" 

    const val appcompat: String = "1.0.2" 

    const val constraintlayout: String = "1.1.3" 

    const val core_ktx: String = "1.0.1" 

    const val fragment_ktx: String = "1.0.0" 

    const val androidx_lifecycle: String = "2.0.0" 

    const val paging_runtime: String = "2.0.0" 

    const val recyclerview: String = "1.0.0" 

    const val androidx_room: String = "2.0.0" 

    const val espresso_core: String = "3.1.1" 

    const val androidx_test: String = "1.1.1"

    const val com_airbnb_android: String = "3.1.0"

    const val aapt2: String = "3.3.0-5013011"

    const val com_android_tools_build_gradle: String = "3.3.0"

    const val lint_gradle: String = "26.3.0" 

    const val com_github_bumptech_glide: String = "4.8.0" 

    const val ktlint: String = "0.29.0"

    const val exoplayer: String = "2.9.3"

    const val com_google_dagger: String = "2.21"

    const val firebase_crash: String = "16.2.1" 

    const val google_services: String = "4.2.0" 

    const val rxrelay: String = "2.1.0"

    const val timber: String = "4.7.1"

    const val com_squareup_leakcanary: String = "1.6.3" 

    const val com_squareup_moshi: String = "1.8.0" 

    const val com_squareup_okhttp3: String = "3.12.1" 

    const val com_squareup_retrofit2: String = "2.5.0" 

    const val com_uber_autodispose: String = "1.1.0" 

    const val com_vanniktech_android_junit_jacoco_gradle_plugin: String = "0.13.0" 

    const val gradle_android_junit_jacoco_plugin: String = "0.13.0" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2"

    const val io_gitlab_arturbosch_detekt: String = "1.0.0-RC12"

    const val mockk: String = "1.9" 

    const val rxandroid: String = "2.1.0" 

    const val rxjava: String = "2.2.5" 

    const val rxkotlin: String = "2.3.0"

    const val junit: String = "4.12"

    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String = "1.1.1" 

    const val org_jacoco: String = "0.8.2" 

    const val org_jetbrains_kotlin: String = "1.3.11" 

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.1.1"

        const val currentVersion: String = "5.1.1"

        const val nightlyVersion: String = "5.2-20190117003518+0000"

        const val releaseCandidate: String = ""
    }
}
