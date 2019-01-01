package com.nicholasdoglio.persistence.local

import androidx.room.RoomDatabase
import com.nicholasdoglio.persistence.local.dao.RedditPostDao

/**
 * @author Nicholas Doglio
 */
//@Database(entities = [(RedditPost::class)], version = 1, exportSchema = true)
abstract class AwwGalleryDatabase : RoomDatabase() {
  abstract fun redditPostDao(): RedditPostDao
}
//TODO Work on migration method
