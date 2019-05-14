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

package com.nicholasdoglio.redditapi

// TODO organize this better

internal object PostsHints {
    internal const val RICH_VIDEO = "rich:video"
    internal const val HOST_VIDEO = "hosted:video"
    internal const val IMAGE = "image"
    internal const val LINK = "link"
}

internal object Domains {
    internal const val REDDIT_VIDEO = "v.redd.it"
    internal const val REDDIT_IMAGE = "i.redd.it"
    internal const val GFYCAT = "gfycat.com"
    internal const val IMGUR = "i.imgur.com" // TODO look for gallery in imgur URL
    internal const val IMGUR_SHORTENED = "imgur.com"
    internal const val YOUTUBE = "youtube.com"
    internal const val YOUTUBE_SHORTENED = "youtu.be"
}

internal const val SUBREDDIT_TYPE_PUBLIC = "public"