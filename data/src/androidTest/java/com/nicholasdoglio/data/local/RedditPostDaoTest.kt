package com.nicholasdoglio.data.local

import com.nicholasdoglio.data.source.local.AwwGalleryDatabase
import org.junit.After
import org.junit.Before

/**
 * @author Nicholas Doglio
 */
class RedditPostDaoTest {

    private lateinit var redditDatabase: AwwGalleryDatabase

    @Before
    fun setUp() {
//        redditDatabase = Room.inMemoryDatabaseBuilder(
//            RuntimeEnvironment.application.applicationContext,
//            AwwGalleryDatabase::class.java
//        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        redditDatabase.close()
    }
}