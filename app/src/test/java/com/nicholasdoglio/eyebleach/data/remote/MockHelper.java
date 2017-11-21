package com.nicholasdoglio.eyebleach.data.remote;

import com.nicholasdoglio.eyebleach.data.model.reddit.Child;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.data.model.reddit.Multireddit;
import com.nicholasdoglio.eyebleach.data.model.reddit.MultiredditData;
import java.util.ArrayList;
import java.util.List;

class MockHelper {

  static final String MockJSON =
      "{\"kind\": \"Listing\", \"data\": { \"modhash\": \"d6p44hc3mlbeef1c20d8b703685b998a71a54e02befa215fde\", \"children\": [ { \"kind\": \"t3\", \"data\": { \"subreddit\": \"aww\", \"selftext\": \"\", \"id\": \"7bwtiu\", \"over_18\": false, \"thumbnail\": \"https://b.thumbs.redditmedia.com/wwP80gysG904aHockWakamh51h07U2GXb_r25pPXxJE.jpg\", \"permalink\": \"/r/aww/comments/7bwtiu/i_havent_found_what_they_broke_yet/\", \"url\": \"https://imgur.com/Ke8Uxel\"} }], \"after\": \"t3_7bwtiu\"} }";

  Multireddit getMulti() {
    Multireddit multireddit = new Multireddit();
    MultiredditData multiredditData = new MultiredditData();
    Child child = new Child();
    ChildData childData = new ChildData();
    List<Child> childList = new ArrayList();

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
}
