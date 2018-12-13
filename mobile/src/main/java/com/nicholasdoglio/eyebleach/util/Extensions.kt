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