package com.nicholasdoglio.persistence.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * @author Nicholas Doglio
 */
@Dao
interface RedditPostDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPosts(redditPosts: List<String>)

  //    @Query("SELECT * FROM RedditPost")
  fun observePosts(): DataSource.Factory<Int, String>
}