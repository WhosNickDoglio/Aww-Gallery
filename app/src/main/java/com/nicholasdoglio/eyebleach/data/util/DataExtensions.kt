/*
 * MIT License
 *
 *   Copyright (c) 2019. Nicholas Doglio
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package com.nicholasdoglio.eyebleach.data.util

import com.nicholasdoglio.eyebleach.data.ChildData
import com.nicholasdoglio.eyebleach.data.ListingResponse
import com.nicholasdoglio.eyebleach.data.RedditPost

fun ListingResponse.toRedditPosts(): List<RedditPost> = this.data.children.asSequence()
    .filter { !it.data.over18 }
    .filter { it.data.url.contains(".jpg") || it.data.url.contains(".png") }
    .map { it.data.toRedditPost() }
    .toList()

fun ChildData.toRedditPost(): RedditPost =
    RedditPost(
        url = url,
        name = name,
        thumbnail = thumbnail,
        permalink = permalink
    )

inline val RedditPost.redditUrl: String
    get() = "https://reddit.com$permalink"
