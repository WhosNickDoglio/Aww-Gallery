package com.nicholasdoglio.eyebleach.di

import com.nicholasdoglio.eyebleach.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Nicholas Doglio
 */
@Module
abstract class MainActivityBindingModule {

  @ContributesAndroidInjector(modules = [(FragmentBindingModule::class)])
  abstract fun contributeMainActivity(): MainActivity
}

