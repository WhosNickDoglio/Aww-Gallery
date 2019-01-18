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

package com.nicholasdoglio.remote.entities

import com.squareup.moshi.Json

data class DataX(
    @Json(name = "approved_at_utc") val approvedAtUtc: Any?, // null
    @Json(name = "subreddit") val subreddit: String, // Catloaf
    @Json(name = "selftext") val selftext: String,
    @Json(name = "user_reports") val userReports: List<Any>,
    @Json(name = "saved") val saved: Boolean, // false
    @Json(name = "mod_reason_title") val modReasonTitle: Any?, // null
    @Json(name = "gilded") val gilded: Int, // 0
    @Json(name = "clicked") val clicked: Boolean, // false
    @Json(name = "title") val title: String, // Shelter cats are the best cats.
    @Json(name = "link_flair_richtext") val linkFlairRichtext: List<Any>,
    @Json(name = "subreddit_name_prefixed") val subredditNamePrefixed: String, // r/Catloaf
    @Json(name = "hidden") val hidden: Boolean, // false
    @Json(name = "pwls") val pwls: Int, // 6
    @Json(name = "link_flair_css_class") val linkFlairCssClass: Any?, // null
    @Json(name = "downs") val downs: Int, // 0
    @Json(name = "thumbnail_height") val thumbnailHeight: Int, // 140
    @Json(name = "hide_score") val hideScore: Boolean, // false
    @Json(name = "name") val name: String, // t3_95l20z
    @Json(name = "quarantine") val quarantine: Boolean, // false
    @Json(name = "link_flair_text_color") val linkFlairTextColor: String, // dark
    @Json(name = "author_flair_background_color") val authorFlairBackgroundColor: Any?, // null
    @Json(name = "subreddit_type") val subredditType: String, // public
    @Json(name = "ups") val ups: Int, // 3294
    @Json(name = "domain") val domain: String, // i.redd.it
    @Json(name = "media_embed") val mediaEmbed: MediaEmbed,
    @Json(name = "thumbnail_width") val thumbnailWidth: Int, // 140
    @Json(name = "author_flair_template_id") val authorFlairTemplateId: Any?, // null
    @Json(name = "is_original_content") val isOriginalContent: Boolean, // false
    @Json(name = "secure_media") val secureMedia: Any?, // null
    @Json(name = "is_reddit_media_domain") val isRedditMediaDomain: Boolean, // true
    @Json(name = "is_meta") val isMeta: Boolean, // false
    @Json(name = "category") val category: Any?, // null
    @Json(name = "secure_media_embed") val secureMediaEmbed: SecureMediaEmbed,
    @Json(name = "link_flair_text") val linkFlairText: Any?, // null
    @Json(name = "can_mod_post") val canModPost: Boolean, // false
    @Json(name = "score") val score: Int, // 3294
    @Json(name = "approved_by") val approvedBy: Any?, // null
    @Json(name = "thumbnail") val thumbnail: String,
    // https://b.thumbs.redditmedia.com/xyI_i7UrGlRb1lLpehX_JUAsGxNp75T78D0IieUHP-A.jpg
    @Json(name = "edited") val edited: Boolean, // false
    @Json(name = "author_flair_css_class") val authorFlairCssClass: Any?, // null
    @Json(name = "author_flair_richtext") val authorFlairRichtext: List<Any>,
    @Json(name = "post_hint") val postHint: String, // image
    @Json(name = "content_categories") val contentCategories: Any?, // null
    @Json(name = "is_self") val isSelf: Boolean, // false
    @Json(name = "mod_note") val modNote: Any?, // null
    @Json(name = "created") val created: Double, // 1533755292.0
    @Json(name = "link_flair_type") val linkFlairType: String, // text
    @Json(name = "wls") val wls: Int, // 6
    @Json(name = "banned_by") val bannedBy: Any?, // null
    @Json(name = "author_flair_type") val authorFlairType: String, // text
    @Json(name = "contest_mode") val contestMode: Boolean, // false
    @Json(name = "selftext_html") val selftextHtml: Any?, // null
    @Json(name = "likes") val likes: Any?, // null
    @Json(name = "suggested_sort") val suggestedSort: Any?, // null
    @Json(name = "banned_at_utc") val bannedAtUtc: Any?, // null
    @Json(name = "view_count") val viewCount: Any?, // null
    @Json(name = "archived") val archived: Boolean, // false
    @Json(name = "no_follow") val noFollow: Boolean, // false
    @Json(name = "is_crosspostable") val isCrosspostable: Boolean, // true
    @Json(name = "pinned") val pinned: Boolean, // false
    @Json(name = "over_18") val over18: Boolean, // false
    @Json(name = "preview") val preview: Preview,
    @Json(name = "media_only") val mediaOnly: Boolean, // false
    @Json(name = "link_flair_template_id") val linkFlairTemplateId: Any?, // null
    @Json(name = "can_gild") val canGild: Boolean, // true
    @Json(name = "spoiler") val spoiler: Boolean, // false
    @Json(name = "locked") val locked: Boolean, // false
    @Json(name = "author_flair_text") val authorFlairText: Any?, // null
    @Json(name = "rte_mode") val rteMode: String, // markdown
    @Json(name = "visited") val visited: Boolean, // false
    @Json(name = "num_reports") val numReports: Any?, // null
    @Json(name = "distinguished") val distinguished: Any?, // null
    @Json(name = "subreddit_id") val subredditId: String, // t5_2z2ak
    @Json(name = "mod_reason_by") val modReasonBy: Any?, // null
    @Json(name = "removal_reason") val removalReason: Any?, // null
    @Json(name = "link_flair_background_color") val linkFlairBackgroundColor: String,
    @Json(name = "id") val id: String, // 95l20z
    @Json(name = "report_reasons") val reportReasons: Any?, // null
    @Json(name = "author") val author: String, // Chrisybells
    @Json(name = "num_crossposts") val numCrossposts: Int, // 0
    @Json(name = "num_comments") val numComments: Int, // 35
    @Json(name = "send_replies") val sendReplies: Boolean, // true
    @Json(name = "whitelist_status") val whitelistStatus: String, // all_ads
    @Json(name = "mod_reports") val modReports: List<Any>,
    @Json(name = "author_flair_text_color") val authorFlairTextColor: Any?, // null
    @Json(name = "permalink") val permalink: String,
    // /r/Catloaf/comments/95l20z/shelter_cats_are_the_best_cats/
    @Json(name = "parent_whitelist_status") val parentWhitelistStatus: String, // all_ads
    @Json(name = "stickied") val stickied: Boolean, // false
    @Json(name = "url") val url: String, // https://i.redd.it/2ccqejmrque11.jpg
    @Json(name = "subreddit_subscribers") val subredditSubscribers: Int, // 139867
    @Json(name = "created_utc") val createdUtc: Double, // 1533726492.0
    @Json(name = "media") val media: Any?, // null
    @Json(name = "is_video") val isVideo: Boolean // false
)