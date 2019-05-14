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

import com.nicholasdoglio.redditapi.model.AwwGalleryPost
import com.nicholasdoglio.redditapi.model.PostType
import com.nicholasdoglio.redditapi.model.Source
import com.nicholasdoglio.redditapi.response.Data
import com.nicholasdoglio.redditapi.response.ListingResponse

class RedditMapper : Mapper<ListingResponse> {

    override fun convert(response: ListingResponse): List<AwwGalleryPost> {
        // 🤷‍ TODO Immutable collections coming eventually? https://github.com/Kotlin/kotlinx.collections.immutable
        val list = mutableListOf<AwwGalleryPost>()

        val posts = response.data.children

        posts.forEach {
            // check over18
            it.data.over18?.let { safeForWork ->

                if (safeForWork) {

                    list.add(
                        // TODO I think this needs more information
                        AwwGalleryPost(
                            id = it.data.name!!,
                            type = determinePostType(it.data),
                            url = it.data.url!!, // TODO other ways to find URL?
                            source = determineSourceType(it.data)
                        )
                    )
                }
            }
        }

        return list
    }

    // TODO can I make this better?
    private fun determinePostType(item: Data): PostType {
        val isVideo: Boolean? = item.isVideo
        val domain: String? = item.domain
        val hint: String? = item.postHint

        isVideo?.run { if (this) return PostType.VIDEO }

        hint?.run {
            return when (this) {
                PostsHints.HOST_VIDEO,
                PostsHints.RICH_VIDEO -> PostType.VIDEO

                PostsHints.IMAGE -> PostType.PHOTO

                PostsHints.LINK -> TODO() // This could be albums, or youtube?
                else -> PostType.UNKNOWN
            }
        }

        domain?.let {
            when (it) {
                Domains.REDDIT_VIDEO,
                Domains.GFYCAT -> PostType.VIDEO

                Domains.REDDIT_IMAGE -> PostType.PHOTO

                // TODO IMGUR can also return gifs or albums need to work on that
                Domains.IMGUR,
                Domains.IMGUR_SHORTENED -> PostType.PHOTO

                Domains.YOUTUBE,
                Domains.YOUTUBE_SHORTENED -> PostType.YOUTUBE
                else -> PostType.UNKNOWN
            }
        }

        return PostType.UNKNOWN
    }

    private fun determineSourceType(item: Data): Source {
        val url = item.url

        return if (url!!.contains(Domains.YOUTUBE) || url.contains(Domains.YOUTUBE_SHORTENED)) {
            Source.Youtube(url)
        } else {
            Source.Reddit(item.permalink!!)
        }
    }
}
