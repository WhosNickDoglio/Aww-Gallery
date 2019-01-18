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

package com.nicholasdoglio.eyebleach.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * @author Nicholas Doglio
 */

/**
 * Removes some boilerplate with inflating views
 * Used in onCreateView() methods in fragments and onCreateViewHolder() methods in adapters
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/**
 * Removes boilerplate with navigating between fragments
 * Used in NavigationController
 */
fun AppCompatActivity.showFragment(
    fragment: androidx.fragment.app.Fragment,
    tag: String,
    name: String,
    transition: Int,
    @IdRes containerViewId: Int
) {
    supportFragmentManager
        .beginTransaction()
        .replace(containerViewId, fragment, tag)
        .addToBackStack(name)
        .setTransition(transition)
        .setReorderingAllowed(true)
        .commit()
}

/** Sets up toolbars in all the fragments */
fun androidx.fragment.app.Fragment.setupToolbar(
    activity: AppCompatActivity,
    toolbar: Toolbar,
    title: String = "",
    optionsMenu: Boolean = false
) {
    activity.setSupportActionBar(toolbar)
    activity.supportActionBar?.setDisplayShowTitleEnabled(false)
    toolbar.title = title
    setHasOptionsMenu(optionsMenu)
}