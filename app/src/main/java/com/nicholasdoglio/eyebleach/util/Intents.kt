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
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import com.nicholasdoglio.eyebleach.ui.about.AboutActivity
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailActivity

/**
 * @author Nicholas Doglio
 */
object Intents {

    fun openWebPage(context: Context, url: String) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun shareUrl(context: Context, url: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.type = "text/plain"
        context.startActivity(Intent.createChooser(shareIntent, "Share your cute animals via: "))
    }

    fun composeEmail(context: Context) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto: NicholasDoglio@Gmail.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Aww Gallery Feedback")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun startDetailActivity(view: View, position: Int) {
        val photoDetailIntent = Intent(view.context, PhotoDetailActivity::class.java)
        photoDetailIntent.putExtra("POSITION", position)
        view.context.startActivity(photoDetailIntent)
    }

    fun startDetailActivity(view: View, id: String) {
        val photoDetailIntent = Intent(view.context, PhotoDetailActivity::class.java)
        photoDetailIntent.putExtra("ID", id)
        view.context.startActivity(photoDetailIntent)
    }

    fun startAboutActivity(context: Context) {
        val aboutIntent = Intent(context, AboutActivity::class.java)
        context.startActivity(aboutIntent)
    }

    fun provideVersion(context: Context): String {
        var version = ""

        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            version = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return "Version: $version"
    }
}