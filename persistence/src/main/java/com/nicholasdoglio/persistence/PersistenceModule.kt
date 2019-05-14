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

package com.nicholasdoglio.persistence

import android.app.Application
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import schema.AwwGalleryPost
import schema.AwwGalleryPostQueries
import javax.inject.Singleton

@Module
object PersistenceModule {

    @Singleton
    @Provides
    @JvmStatic
    fun databaseDriver(application: Application): AndroidSqliteDriver =
        AndroidSqliteDriver(Database.Schema, application, "posts.db")

    @Singleton
    @Provides
    @JvmStatic
    fun database(driver: AndroidSqliteDriver): Database =
        Database.invoke(
            driver = driver,
            awwGalleryPostAdapter = AwwGalleryPost.Adapter(
                typeAdapter = EnumColumnAdapter(),
                tagAdapter = object : ColumnAdapter<Tag, String> {
                    override fun decode(databaseValue: String): Tag {
                        // TODO I don' like this
                        if (databaseValue.contains("Subreddit")) {
                            return Tag.Subreddit(databaseValue.trimMargin("Subreddit(sub=").dropLast(1))
                        }

                        if (databaseValue.contains("Hashtag")) {
                            return Tag.Hashtag(databaseValue.trimMargin("Hashtag(hashtag=").dropLast(1))
                        }

                        return Tag.Subreddit("")
                    }

                    override fun encode(value: Tag): String = value.toString()
                }
            )
        )

    @Singleton
    @Provides
    @JvmStatic
    fun postQueries(database: Database): AwwGalleryPostQueries = database.awwGalleryPostQueries
}