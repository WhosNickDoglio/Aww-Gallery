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

import android.content.Context
import com.nicholasdoglio.core.BuildConfig

/**
 * @author Nicholas Doglio
 */
class Intents {

    // TODO move back to Anko? Move this into extension functions?

    fun openWebPage(context: Context, url: String) {
//        context.browse(url)
    }

    fun shareUrl(context: Context, url: String) {
// TODO look into ShareCompat
//        val shareIntent = ShareCompat.IntentBuilder.from(activity)
//            .setType("text/plain")
//            .setText(url)
//            .setChooserTitle("Share your cute animals via: ")
//            .intent
//        context.share(url,"Share your cute animals via: ")

//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
//        shareIntent.type = "text/plain"
//        context.startActivity(Intent.createChooser(shareIntent, "Share your cute animals via: "))
    }

    fun composeEmail(context: Context) {
//        context.email("NicholasDoglio@Gmail.com", "Aww Gallery Feedback")
    }

    fun provideVersion(context: Context): String { // This is unnecessary, move later
        return "Version: ${BuildConfig.VERSION_NAME}"
    }
}