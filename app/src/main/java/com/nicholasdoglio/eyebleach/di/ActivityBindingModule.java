/*
    Aww Gallery
    Copyright (C) 2017  Nicholas Doglio

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nicholasdoglio.eyebleach.di;

import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailActivity;
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailModule;
import com.nicholasdoglio.eyebleach.ui.photogrid.PhotoGridActivity;
import com.nicholasdoglio.eyebleach.ui.photogrid.PhotoGridModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Nicholas Doglio
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = PhotoGridModule.class)
    abstract PhotoGridActivity bindPhotoGridActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PhotoDetailModule.class)
    abstract PhotoDetailActivity bindPhotoDetailActivity();
}