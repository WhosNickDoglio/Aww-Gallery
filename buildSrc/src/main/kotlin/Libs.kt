object Libs {

    object Coroutines {
        const val android: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val core: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
            Versions.kotlin

    const val dagger: String = "com.google.dagger:dagger:${Versions.dagger}"

    const val dagger_compiler: String = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val coil: String = "io.coil-kt:coil:${Versions.coil}"

    const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    const val leakcanary_android: String = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val play_publisher: String = "com.github.triplet.gradle:play-publisher:${Versions.playPublisher}"

    const val appcompat: String = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val material: String = "com.google.android.material:material:${Versions.material}"

    const val timber: String = "com.jakewharton.timber:timber:${Versions.timber}"

    const val junit: String = "junit:junit:${Versions.junit}"

    const val truth: String = "com.google.truth:truth:${Versions.truth}"

    const val retrofit: String = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    const val retrofitMoshiConverter: String =
            "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val okhttp: String = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    const val okhttpLogging: String = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val moshi: String = "com.squareup.moshi:moshi:${Versions.moshi}"

    const val moshiCodeGen: String = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}
