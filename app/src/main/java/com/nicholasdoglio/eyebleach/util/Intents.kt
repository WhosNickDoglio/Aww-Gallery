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
package com.nicholasdoglio.eyebleach.util

import android.content.Context
import android.content.Intent
import com.nicholasdoglio.eyebleach.BuildConfig
import org.jetbrains.anko.browse
import org.jetbrains.anko.email


/**
 * @author Nicholas Doglio
 */
class Intents {

    fun openWebPage(context: Context, url: String) {
        context.browse(url)
    }

    fun shareUrl(context: Context, url: String) {
//TODO look into ShareCompat
//        val shareIntent = ShareCompat.IntentBuilder.from(activity)
//            .setType("text/plain")
//            .setText(url)
//            .setChooserTitle("Share your cute animals via: ")
//            .intent

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.type = "text/plain"
        context.startActivity(Intent.createChooser(shareIntent, "Share your cute animals via: "))
    }

    fun composeEmail(context: Context) {
        context.email("NicholasDoglio@Gmail.com", "Aww Gallery Feedback")
    }

    fun provideVersion(context: Context): String { //This is unnecessary, move later
        return "Version: ${BuildConfig.VERSION_NAME}"
    }
}