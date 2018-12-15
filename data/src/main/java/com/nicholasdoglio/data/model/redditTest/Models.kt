package com.nicholasdoglio.data.model.redditTest

/*

data class ListingResponse(
    @Json(name = "kind") val kind: String, //Listing
    @Json(name = "data") val listingData: ListingData
)

data class ListingData(
    @Json(name = "after") val after: String, //t3_8cwyjt
    @Json(name = "dist") val dist: Int, //10
    @Json(name = "modhash") val modhash: String, //6au95ynx42db56da7965ff71443ee80ef394d396d413adb9e9
    @Json(name = "children") val children: List<Children>
)

data class Children(
    @Json(name = "kind") val kind: String, //t3
    @Json(name = "data") val data: Data
)

data class Data(
    @Json(name = "subreddit") val subreddit: String, //aww
    @Json(name = "selftext") val selftext: String,
    @Json(name = "user_reports") val userReports: List<Any>,
    @Json(name = "saved") val saved: Boolean, //false
    @Json(name = "gilded") val gilded: Int, //0
    @Json(name = "clicked") val clicked: Boolean, //false
    @Json(name = "title") val title: String, //Caught my kitty lounging
    @Json(name = "link_flair_richtext") val linkFlairRichtext: List<Any>,
    @Json(name = "subreddit_name_prefixed") val subredditNamePrefixed: String, //r/aww
    @Json(name = "hidden") val hidden: Boolean, //false
    @Json(name = "downs") val downs: Int, //0
    @Json(name = "thumbnail_height") val thumbnailHeight: Int, //140
    @Json(name = "parent_whitelist_status") val parentWhitelistStatus: String, //all_ads
    @Json(name = "hide_score") val hideScore: Boolean, //false
    @Json(name = "name") val name: String, //t3_8cxamw
    @Json(name = "quarantine") val quarantine: Boolean, //false
    @Json(name = "link_flair_text_color") val linkFlairTextColor: String, //dark
    @Json(name = "subreddit_type") val subredditType: String, //public
    @Json(name = "ups") val ups: Int, //49381
    @Json(name = "domain") val domain: String, //v.redd.it
    @Json(name = "media_embed") val mediaEmbed: MediaEmbed,
    @Json(name = "thumbnail_width") val thumbnailWidth: Int, //140
    @Json(name = "is_original_content") val isOriginalContent: Boolean, //false
    @Json(name = "secure_media") val secureMedia: SecureMedia,
    @Json(name = "is_reddit_media_domain") val isRedditMediaDomain: Boolean, //true
    @Json(name = "secure_media_embed") val secureMediaEmbed: SecureMediaEmbed,
    @Json(name = "can_mod_post") val canModPost: Boolean, //false
    @Json(name = "score") val score: Int, //49381
    @Json(name = "thumbnail") val thumbnail: String, //https://b.thumbs.redditmedia.com/oDFZBofgGzEj0tkjealY2-bDMrdlK3yXt0HisXzcUQU.jpg
    @Json(name = "edited") val edited: Boolean, //false
    @Json(name = "brand_safe") val brandSafe: Boolean, //true
    @Json(name = "author_flair_richtext") val authorFlairRichtext: List<Any>,
    @Json(name = "post_hint") val postHint: String, //hosted:video
    @Json(name = "is_self") val isSelf: Boolean, //false
    @Json(name = "created") val created: Int, //1524006975
    @Json(name = "link_flair_type") val linkFlairType: String, //text
    @Json(name = "author_flair_type") val authorFlairType: String, //text
    @Json(name = "contest_mode") val contestMode: Boolean, //false
    @Json(name = "archived") val archived: Boolean, //false
    @Json(name = "no_follow") val noFollow: Boolean, //false
    @Json(name = "is_crosspostable") val isCrosspostable: Boolean, //true
    @Json(name = "pinned") val pinned: Boolean, //false
    @Json(name = "over_18") val over18: Boolean, //false
    @Json(name = "preview") val preview: Preview,
    @Json(name = "media") val media: Media,
    @Json(name = "can_gild") val canGild: Boolean, //true
    @Json(name = "spoiler") val spoiler: Boolean, //false
    @Json(name = "locked") val locked: Boolean, //false
    @Json(name = "rte_mode") val rteMode: String, //markdown
    @Json(name = "visited") val visited: Boolean, //false
    @Json(name = "subreddit_id") val subredditId: String, //t5_2qh1o
    @Json(name = "id") val id: String, //8cxamw
    @Json(name = "author") val author: String, //kaleonsale
    @Json(name = "num_crossposts") val numCrossposts: Int, //5
    @Json(name = "num_comments") val numComments: Int, //580
    @Json(name = "send_replies") val sendReplies: Boolean, //true
    @Json(name = "permalink") val permalink: String, ///r/aww/comments/8cxamw/caught_my_kitty_lounging/
    @Json(name = "whitelist_status") val whitelistStatus: String, //all_ads
    @Json(name = "stickied") val stickied: Boolean, //false
    @Json(name = "url") val url: String, //https://v.redd.it/ni87ivnijhs01
    @Json(name = "subreddit_subscribers") val subredditSubscribers: Int, //16964557
    @Json(name = "created_utc") val createdUtc: Int, //1523978175
    @Json(name = "mod_reports") val modReports: List<Any>,
    @Json(name = "is_video") val isVideo: Boolean //true
)

data class Preview(
    @Json(name = "images") val images: List<Image>,
    @Json(name = "enabled") val enabled: Boolean //false
)

data class Image(
    @Json(name = "source") val source: Source,
    @Json(name = "resolutions") val resolutions: List<Resolution>,
    @Json(name = "variants") val variants: Variants,
    @Json(name = "id") val id: String //Xh1mSc-JWrJfaLH3vrKKY2KcD8bKfK57IhE4zKqLc5A
)

data class Resolution(
    @Json(name = "url") val url: String, //https://i.redditmedia.com/OMv8H4uLi1n-x8nlEaGwzddsAxFdfJCkqC5MxcnXbds.png?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=eb60e74af1195a19f46f651119528beb
    @Json(name = "width") val width: Int, //108
    @Json(name = "height") val height: Int //192
)

data class Source(
    @Json(name = "url") val url: String, //https://i.redditmedia.com/OMv8H4uLi1n-x8nlEaGwzddsAxFdfJCkqC5MxcnXbds.png?s=9d5f2c7495146d8bce98f5e978c1cc93
    @Json(name = "width") val width: Int, //720
    @Json(name = "height") val height: Int //1280
)

data class Variants(
    val test: String //This was empty
)

data class Media(
    @Json(name = "reddit_video") val redditVideo: RedditVideo
)

//This might be key to making gifs work 
data class RedditVideo(
    @Json(name = "fallback_url") val fallbackUrl: String, //https://v.redd.it/ni87ivnijhs01/DASH_4_8_M
    @Json(name = "height") val height: Int, //720
    @Json(name = "width") val width: Int, //404
    @Json(name = "scrubber_media_url") val scrubberMediaUrl: String, //https://v.redd.it/ni87ivnijhs01/DASH_600_K
    @Json(name = "dash_url") val dashUrl: String, //https://v.redd.it/ni87ivnijhs01/DASHPlaylist.mpd
    @Json(name = "duration") val duration: Int, //11
    @Json(name = "hls_url") val hlsUrl: String, //https://v.redd.it/ni87ivnijhs01/HLSPlaylist.m3u8
    @Json(name = "is_gif") val isGif: Boolean, //true
    @Json(name = "transcoding_status") val transcodingStatus: String //completed
)

data class SecureMediaEmbed(
    val test: String//This was empty
)

data class MediaEmbed(
    val test: String //This was empty
)

data class SecureMedia(
    @Json(name = "reddit_video") val redditVideo: RedditVideo
)*/
