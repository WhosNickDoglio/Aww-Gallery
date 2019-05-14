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

package com.nicholasdoglio.redditapi.response

// TODO look into nullability 

// @JsonClass(generateAdapter = true)
// data class ListingResponse(val kind: String, val data: Listing)
//
// @JsonClass(generateAdapter = true)
// data class Listing(val dist: Int, val after: String, val children: List<ChildData>)
//
// @JsonClass(generateAdapter = true)
// data class ChildData(val data: Data)
//
// @JsonClass(generateAdapter = true)
// data class Data(
//     @Json(name = "domain")
//     val domain: String?,
//     @Json(name = "is_video")
//     val isVideo: Boolean?,
//     @Json(name = "media")
//     val media: Media?, // I don't think I need this
//     @Json(name = "name")
//     val name: String?, // t3_b92foq
//     @Json(name = "over_18")
//     val over18: Boolean?,
//     @Json(name = "permalink")
//     val permalink: String?,
//     @Json(name = "post_hint")
//     val postHint: String?,
//     @Json(name = "subreddit")
//     val subreddit: String?,
//     @Json(name = "url")
//     val url: String?
// )
//
// data class Media(
//     @Json(name = "is_video")
//     val isVideo: Boolean?, // false
//     @Json(name = "media")
//     val media: MediaData?
// )
//
// data class MediaData(
//     @Json(name = "reddit_video")
//     val redditVideo: RedditVideo?,
//     @Json(name = "oembed")
//     val oembed: Oembed?,
//     @Json(name = "type")
//     val type: String? // gfycat.com
// )
//
// data class RedditVideo(
//     @Json(name = "dash_url")
//     val dashUrl: String?, // https://v.redd.it/k6vm2rks67w21/DASHPlaylist.mpd
//     @Json(name = "duration")
//     val duration: Int?, // 11
//     @Json(name = "fallback_url")
//     val fallbackUrl: String?, // https://v.redd.it/k6vm2rks67w21/DASH_480?source=fallback
//     @Json(name = "height")
//     val height: Int?, // 480
//     @Json(name = "hls_url")
//     val hlsUrl: String?, // https://v.redd.it/k6vm2rks67w21/HLSPlaylist.m3u8
//     @Json(name = "is_gif")
//     val isGif: Boolean?, // true
//     @Json(name = "scrubber_media_url")
//     val scrubberMediaUrl: String?, // https://v.redd.it/k6vm2rks67w21/DASH_96
//     @Json(name = "transcoding_status")
//     val transcodingStatus: String?, // completed
//     @Json(name = "width")
//     val width: Int? // 342
// )
//
// data class Oembed(
//     @Json(name = "description")
//     val description: String?, // Watch pupper is annoy GIF by m'vanilla (@uncommonvanilla) on Gfycat. Discover more bean, flashbackfriday, goldenretriever, goldenretrieverpuppy, goldensofig, linguini, puppies, shihtzu, shihtzusofinstagram, silly GIFs on Gfycat
//     @Json(name = "height")
//     val height: Int?, // 480
//     @Json(name = "html")
//     val html: String?, // &lt;iframe class="embedly-embed" src="https://cdn.embedly.com/widgets/media.html?src=https%3A%2F%2Fgfycat.com%2Fifr%2Fsoftunfortunateaegeancat&amp;url=https%3A%2F%2Fgfycat.com%2Fsoftunfortunateaegeancat&amp;image=https%3A%2F%2Fthumbs.gfycat.com%2FSoftUnfortunateAegeancat-size_restricted.gif&amp;key=ed8fa8699ce04833838e66ce79ba05f1&amp;type=text%2Fhtml&amp;schema=gfycat" width="480" height="480" scrolling="no" frameborder="0" allow="autoplay; fullscreen" allowfullscreen="true"&gt;&lt;/iframe&gt;
//     @Json(name = "provider_name")
//     val providerName: String?, // Gfycat
//     @Json(name = "provider_url")
//     val providerUrl: String?, // https://gfycat.com
//     @Json(name = "thumbnail_height")
//     val thumbnailHeight: Int?, // 320
//     @Json(name = "thumbnail_url")
//     val thumbnailUrl: String?, // https://thumbs.gfycat.com/SoftUnfortunateAegeancat-size_restricted.gif
//     @Json(name = "thumbnail_width")
//     val thumbnailWidth: Int?, // 320
//     @Json(name = "title")
//     val title: String?, // pupper is annoy
//     @Json(name = "type")
//     val type: String?, // video
//     @Json(name = "version")
//     val version: String?, // 1.0
//     @Json(name = "width")
//     val width: Int? // 480
// )
