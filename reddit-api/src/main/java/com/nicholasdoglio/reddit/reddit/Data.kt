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

package com.nicholasdoglio.reddit.reddit

import com.squareup.moshi.Json

data class Data(
    @Json(name = "id")
    val id: String?, // b92foq
    @Json(name = "domain")
    val domain: String?, // b92foq
    @Json(name = "is_reddit_media_domain")
    val isRedditMediaDomain: Boolean?, // true
    @Json(name = "is_self")
    val isSelf: Boolean?, // false
    @Json(name = "is_video")
    val isVideo: Boolean?, // true
    @Json(name = "media")
    val media: Media?,
    @Json(name = "media_embed")
    val mediaEmbed: MediaEmbed?,
    @Json(name = "media_only")
    val mediaOnly: Boolean?, // false
    @Json(name = "name")
    val name: String?, // t3_b92foq
    @Json(name = "over_18")
    val over18: Boolean?, // false
    @Json(name = "permalink")
    val permalink: String?, // /r/aww/comments/b92foq/i_think_its_safe_to_say_brodie_approves_of_me/
    @Json(name = "post_hint")
    val postHint: String?, // hosted:video
    @Json(name = "preview")
    val preview: Preview?,
    @Json(name = "secure_media")
    val secureMedia: SecureMedia?,
    @Json(name = "secure_media_embed")
    val secureMediaEmbed: SecureMediaEmbed?,
    @Json(name = "subreddit")
    val subreddit: String?, // aww
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String?, // r/aww
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?
)

// TODO bring back some upvotes or date/timing for sorting