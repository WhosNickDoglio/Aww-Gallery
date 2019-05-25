package com.nicholasdoglio.eyebleach.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.nicholasdoglio.eyebleach.ui.about.AboutFragment
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailFragment
import com.nicholasdoglio.eyebleach.ui.photolist.PhotoListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(PhotoListFragment::class)
    abstract fun bindPhotoListFragment(fragment: PhotoListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PhotoDetailFragment::class)
    abstract fun bindPhotoDetailFragment(fragment: PhotoDetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(AboutFragment::class)
    abstract fun bindAboutFragment(fragment: AboutFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}
