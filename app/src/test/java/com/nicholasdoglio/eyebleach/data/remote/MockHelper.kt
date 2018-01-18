/*
    Aww Gallery
    Copyright (C) 2017  Nicholas Doglio

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nicholasdoglio.eyebleach.data.remote

import com.nicholasdoglio.eyebleach.data.model.reddit.Child
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData
import com.nicholasdoglio.eyebleach.data.model.reddit.RedditListing
import com.nicholasdoglio.eyebleach.data.model.reddit.RedditListingData
import java.util.*

/**
 * @author Nicholas Doglio
 */
class MockHelper {

    val multi: RedditListing
        get() {
            val redditListing = RedditListing()
            val redditListingData = RedditListingData()
            val child = Child()
            val childData = ChildData()
            val childList = ArrayList<Child>()

            childData.subreddit = "aww"
            childData.selftext = ""
            childData.id = "7bwtiu"
            childData.over18 = false
            childData.thumbnail =
                    "https://b.thumbs.redditmedia.com/wwP80gysG904aHockWakamh51h07U2GXb_r25pPXxJE.jpg"
            childData.permalink = "/r/aww/comments/7bwtiu/i_havent_found_what_they_broke_yet/"
            childData.url = "https://imgur.com/Ke8Uxel"

            child.childData = childData
            child.kind = "t3"

            childList.add(child)

            redditListingData.modhash = "d6p44hc3mlbeef1c20d8b703685b998a71a54e02befa215fde"
            redditListingData.children = childList
            redditListingData.after = "t3_7bwtiu"

            redditListing.kind = "Listing"
            redditListing.data = redditListingData

            return redditListing
        }

    fun jsonToString(filename: String): String? {
        // TODO:
        return null
    }

    fun multiFromJson(filename: String): RedditListing? {
        // TODO;
        return null
    }

    companion object {
        val MOCK_JSON =
            "{\"kind\": \"Listing\", \"data\": { \"modhash\": \"d6p44hc3mlbeef1c20d8b703685b998a71a54e02befa215fde\", \"children\": [ { \"kind\": \"t3\", \"data\": { \"subreddit\": \"aww\", \"selftext\": \"\", \"id\": \"7bwtiu\", \"over_18\": false, \"thumbnail\": \"https://b.thumbs.redditmedia.com/wwP80gysG904aHockWakamh51h07U2GXb_r25pPXxJE.jpg\", \"permalink\": \"/r/aww/comments/7bwtiu/i_havent_found_what_they_broke_yet/\", \"url\": \"https://imgur.com/Ke8Uxel\"} }], \"after\": \"t3_7bwtiu\"} }"//This is terrible
    }
}
