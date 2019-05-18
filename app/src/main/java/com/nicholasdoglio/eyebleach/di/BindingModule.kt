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

package com.nicholasdoglio.eyebleach.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicholasdoglio.eyebleach.ui.about.AboutViewModel
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailViewModel
import com.nicholasdoglio.eyebleach.ui.photolist.PhotoListViewModel
import com.nicholasdoglio.eyebleach.util.AppSchedulers
import com.nicholasdoglio.eyebleach.util.SchedulersProvider
import com.nicholasdoglio.eyebleach.worker.ChildWorkerFactory
import com.nicholasdoglio.eyebleach.worker.ClearDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BindingModule {

    @Binds
    abstract fun bindSchedulers(appSchedulers: AppSchedulers): SchedulersProvider

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

    @Binds
    @IntoMap
    @WorkerKey(ClearDataWorker::class)
    abstract fun bindHelloWorldWorker(factory: ClearDataWorker.Factory): ChildWorkerFactory
}