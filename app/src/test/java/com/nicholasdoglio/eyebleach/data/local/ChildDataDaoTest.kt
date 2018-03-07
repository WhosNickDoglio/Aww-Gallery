package com.nicholasdoglio.eyebleach.data.local

import android.arch.persistence.room.Room
import com.nicholasdoglio.eyebleach.data.source.local.RedditPostDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * @author Nicholas Doglio
 */
@RunWith(RobolectricTestRunner::class)
class ChildDataDaoTest {

    private lateinit var redditDatabase: RedditPostDatabase

    @Before
    fun setUp() {
        redditDatabase = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            RedditPostDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        redditDatabase.close()
    }

    @Test
    fun insertData() {

    }

    @Test
    fun getData() {

    }

    @Test
    fun deleteData() {

    }
}