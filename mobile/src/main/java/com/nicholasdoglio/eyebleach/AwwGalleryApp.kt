package com.nicholasdoglio.eyebleach

import android.os.StrictMode
import com.nicholasdoglio.eyebleach.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * @author Nicholas Doglio
 */
class AwwGalleryApp : DaggerApplication() {
  //TODO set up sample data XML

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerAppComponent.builder().application(this).build()

  override fun onCreate() {
    super.onCreate()
    initDebugTools()
    initLeakCanary()
  }

  private fun initDebugTools() {
    if (BuildConfig.DEBUG) { //init all debug tools
      initStrictMode()
      Timber.plant(Timber.DebugTree())
    } else {
//            Timber.plant(ReleaseTree()) TODO set up Release tree
    }
  }

  private fun initStrictMode() {
    StrictMode.setThreadPolicy(
      StrictMode.ThreadPolicy.Builder()
        .detectAll()
        .penaltyDeath()
        .build()
    )

    StrictMode.setVmPolicy(
      StrictMode.VmPolicy.Builder()
        .detectAll()
        .penaltyDeath()
        .build()
    )
  }

  private fun initLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) return
    LeakCanary.install(this)
  }
}