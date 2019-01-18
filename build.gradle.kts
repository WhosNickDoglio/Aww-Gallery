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

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    jcenter()
    mavenCentral()
  }
  dependencies {
      classpath("com.android.tools.build:gradle:3.3.0")
    classpath(kotlin("gradle-plugin", version = "1.3.11"))
    classpath("com.vanniktech:gradle-android-junit-jacoco-plugin:0.13.0")
    classpath("com.google.gms:google-services:4.2.0")
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle.kts files
  }
}

plugins {
  id("io.gitlab.arturbosch.detekt") version("1.0.0-RC12")
  id("de.fayard.buildSrcVersions") version("0.3.2")
  id("com.vanniktech.android.junit.jacoco") version("0.13.0")
}

allprojects {
  repositories {
    google()
    jcenter()
    mavenCentral()
  }
}

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}