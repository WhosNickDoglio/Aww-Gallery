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
package com.nicholasdoglio.eyebleach.util;

import android.support.v7.util.DiffUtil;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;

import java.util.List;
import java.util.Objects;

/**
 * @author Nicholas Doglio
 */
public class RedditListDiffUtil extends DiffUtil.Callback {
    private List<ChildData> oldList;
    private List<ChildData> newList;

    public RedditListDiffUtil(List<ChildData> oldList, List<ChildData> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldList.get(oldItemPosition).getId(), newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ChildData newPost = newList.get(newItemPosition);
        ChildData oldPost = oldList.get(oldItemPosition);
        return Objects.equals(newPost.getId(), oldPost.getId())
                && Objects.equals(newPost.getPermalink(), oldPost.getPermalink())
                && Objects.equals(newPost.getUrl(), oldPost.getUrl())
                && Objects.equals(newPost.getThumbnail(), oldPost.getThumbnail());
    }
}