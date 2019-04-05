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

package com.nicholasdoglio.reddit

// TODO organize this better
object Constants {
    object PostsHints {
        const val RICH_VIDEO = "rich:video"
        const val HOST_VIDEO = "hosted:video"
        const val IMAGE = "image"
        const val LINK = "link"
    }

    object Domains {
        const val REDDIT_VIDEO = "v.redd.it"
        const val REDDIT_IMAGE = "i.redd.it"
        const val GFYCAT = "gfycat.com"
        const val IMGUR = "i.imgur.com" // TODO look for gallery in imgur URL
        const val IMGUR_SHORTENED = "imgur.com"
        const val YOUTUBE = "youtube.com"
        const val YOUTUBE_SHORTENED = "youtu.be"
    }
}