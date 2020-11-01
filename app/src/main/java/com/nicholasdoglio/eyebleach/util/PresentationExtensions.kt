/*
 * MIT License
 *
 *   Copyright (c) 2020. Nicholas Doglio
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package com.nicholasdoglio.eyebleach.util

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nicholasdoglio.eyebleach.R

fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    if (intent.resolveActivity(this.packageManager) != null) {
        this.startActivity(intent)
    }
}

fun Context.shareUrl(url: String) {
    this.startActivity(
        Intent.createChooser(
            Intent(Intent.ACTION_SEND).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            },
            "Share your cute animals via: "
        )
    )
}

val RecyclerView.calculateNumOfColumns: Int
    get() {
        val displayMetrics = context.resources.displayMetrics

        val density = displayMetrics.density

        val dpWidth = displayMetrics.widthPixels / density

        val imageSize = context.resources.getDimension(R.dimen.grid_item_size) / density

        return (dpWidth / imageSize).toInt()
    }

fun Fragment.setStatusBarColor(@ColorInt color: Int = Color.BLACK) {
    ObjectAnimator.ofArgb(
        requireActivity().window,
        "statusBarColor",
        requireActivity().window.statusBarColor,
        color
    ).start()
}
