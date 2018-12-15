package com.nicholasdoglio.eyebleach.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailViewModel
import com.nicholasdoglio.eyebleach.ui.photolist.PhotoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(PhotoListViewModel::class)
  abstract fun bindPhotoListViewModel(noteViewModel: PhotoListViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(PhotoDetailViewModel::class)
  abstract fun bindPhotoDetailViewModel(noreListViewModel: PhotoDetailViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: GalleryViewModelFactory): ViewModelProvider.Factory
}