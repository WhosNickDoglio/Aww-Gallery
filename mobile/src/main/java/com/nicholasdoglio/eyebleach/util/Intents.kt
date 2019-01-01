package com.nicholasdoglio.eyebleach.util

import android.content.Context
import com.nicholasdoglio.eyebleach.BuildConfig

/**
 * @author Nicholas Doglio
 */
class Intents {

  //TODO move back to Anko? Move this into extension functions?

  fun openWebPage(context: Context, url: String) {
//        context.browse(url)
  }

  fun shareUrl(context: Context, url: String) {
//TODO look into ShareCompat
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

  fun provideVersion(context: Context): String { //This is unnecessary, move later
    return "Version: ${BuildConfig.VERSION_NAME}"
  }
}