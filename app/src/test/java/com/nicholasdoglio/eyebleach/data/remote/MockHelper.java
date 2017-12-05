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
package com.nicholasdoglio.eyebleach.data.remote;

import com.nicholasdoglio.eyebleach.data.model.reddit.Child;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.model.reddit.MultiredditData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas Doglio
 */
public class MockHelper {
    public static final String MOCK_JSON = //This is terrible
            "{\"kind\": \"Listing\", \"data\": { \"modhash\": \"d6p44hc3mlbeef1c20d8b703685b998a71a54e02befa215fde\", \"children\": [ { \"kind\": \"t3\", \"data\": { \"subreddit\": \"aww\", \"selftext\": \"\", \"id\": \"7bwtiu\", \"over_18\": false, \"thumbnail\": \"https://b.thumbs.redditmedia.com/wwP80gysG904aHockWakamh51h07U2GXb_r25pPXxJE.jpg\", \"permalink\": \"/r/aww/comments/7bwtiu/i_havent_found_what_they_broke_yet/\", \"url\": \"https://imgur.com/Ke8Uxel\"} }], \"after\": \"t3_7bwtiu\"} }";

    public Multireddit getMulti() {
        Multireddit multireddit = new Multireddit();
        MultiredditData multiredditData = new MultiredditData();
        Child child = new Child();
        ChildData childData = new ChildData();
        List<Child> childList = new ArrayList<Child>();

        childData.setSubreddit("aww");
        childData.setSelftext("");
        childData.setId("7bwtiu");
        childData.setOver18(false);
        childData.setThumbnail("https://b.thumbs.redditmedia.com/wwP80gysG904aHockWakamh51h07U2GXb_r25pPXxJE.jpg");
        childData.setPermalink("/r/aww/comments/7bwtiu/i_havent_found_what_they_broke_yet/");
        childData.setUrl("https://imgur.com/Ke8Uxel");

        child.setChildData(childData);
        child.setKind("t3");

        childList.add(child);

        multiredditData.setModhash("d6p44hc3mlbeef1c20d8b703685b998a71a54e02befa215fde");
        multiredditData.setChildren(childList);
        multiredditData.setAfter("t3_7bwtiu");

        multireddit.setKind("Listing");
        multireddit.setData(multiredditData);

        return multireddit;
    }

    public String jsonToString(String filename) {
        // TODO:
        return null;
    }

    public Multireddit multiFromJson(String filename) {
        // TODO;

        return null;
    }
}
