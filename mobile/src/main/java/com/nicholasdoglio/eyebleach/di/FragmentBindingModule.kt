package com.nicholasdoglio.eyebleach.di

import com.nicholasdoglio.eyebleach.ui.about.AboutFragment
import com.nicholasdoglio.eyebleach.ui.about.libs.LibsFragment
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailFragment
import com.nicholasdoglio.eyebleach.ui.photolist.PhotoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

  @ContributesAndroidInjector
  abstract fun contributesPhotoListFragment(): PhotoListFragment

  @ContributesAndroidInjector
  abstract fun contributesPhotoDetailFragment(): PhotoDetailFragment

  @ContributesAndroidInjector
  abstract fun contributesAboutFragment(): AboutFragment

  @ContributesAndroidInjector
  abstract fun contributesLibsFragment(): LibsFragment
}