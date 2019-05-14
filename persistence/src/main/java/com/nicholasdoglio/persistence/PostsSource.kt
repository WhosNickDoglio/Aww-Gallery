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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import schema.AwwGalleryPost
import schema.AwwGalleryPostQueries
import javax.inject.Inject

class PostsSource @Inject constructor(private val queries: AwwGalleryPostQueries) : LocalSource {

    override suspend fun observeAllPosts(): Flow<List<AwwGalleryPost>> {
        return flowOf(queries.posts().executeAsList())
    }

    override suspend fun observeFavoritePosts(): Flow<List<AwwGalleryPost>> {
        return flowOf(queries.posts().executeAsList())
    }

    override suspend fun observePostsBySubreddit(vararg subreddit: String): Flow<List<AwwGalleryPost>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun postById(id: String): AwwGalleryPost {
        return queries.findPostById(id).executeAsOne()
    }
}