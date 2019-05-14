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

package com.nicholasdoglio.redditapi.model

data class AwwGalleryPost(
    val id: String,
    val type: PostType,
    val url: String,
    val source: Source,
    var isFavorite: Boolean = false
)

// TODO some sort of fallback for unknown types or do I just throw it away?
enum class PostType { PHOTO, VIDEO, ALBUM, YOUTUBE, UNKNOWN }

// TODO is this the better way to do it?
sealed class Tag {
    data class Subreddit(val sub: String) : Tag()
    data class Hashtag(val hashtag: String) : Tag()
}

// TODO add other sources when support is added
sealed class Source(open val url: String) {
    data class Reddit(override val url: String) : Source(url)
    data class Youtube(override val url: String) : Source(url)
}
