package com.nicholasdoglio.eyebleach.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicholasdoglio.eyebleach.ui.about.AboutViewModel
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailViewModel
import com.nicholasdoglio.eyebleach.ui.photolist.PhotoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PhotoDetailViewModel::class)
    abstract fun bindPhotoDetailViewModel(photoDetailViewModel: PhotoDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoListViewModel::class)
    abstract fun bindPhotoListViewModel(photoListViewModel: PhotoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(aboutViewModel: AboutViewModel): ViewModel
}
