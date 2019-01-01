package com.nicholasdoglio.data.model.about

/**
 * @author Nicholas Doglio
 *
 * Base object for information that will be displayed in the About screen RecyclerView
 *
 * @param imageID: drawable asset for icons in About screen
 * @param contentName: String that will be the displayed text for each About item
 * @param contentLink: URL that will be launched upon click of each about item
 */
data class AboutInfo(
  val imageID: Int,
  val contentName: String,
  val contentLink: String
)