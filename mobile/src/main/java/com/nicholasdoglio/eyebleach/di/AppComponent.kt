package com.nicholasdoglio.eyebleach.di

import android.app.Application
import com.nicholasdoglio.eyebleach.AwwGalleryApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Nicholas Doglio
 */
@Singleton
@Component(
  modules = [
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (MainActivityBindingModule::class)]
)
interface AppComponent : AndroidInjector<AwwGalleryApp> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}