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

package com.nicholasdoglio.eyebleach.ui.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.data.about.AboutInfo
import com.nicholasdoglio.eyebleach.data.about.OpenAction
import com.nicholasdoglio.eyebleach.ui.base.AwwGalleryHolder
import com.nicholasdoglio.eyebleach.ui.util.openWebPage
import kotlinx.android.synthetic.main.item_about.*

class AboutAdapter(private val navController: NavController) :
    ListAdapter<AboutInfo, AboutAdapter.AboutViewHolder>(diff) {
    companion object {
        private val diff = object : DiffUtil.ItemCallback<AboutInfo>() {
            override fun areItemsTheSame(oldItem: AboutInfo, newItem: AboutInfo): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: AboutInfo, newItem: AboutInfo): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder =
        AboutViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_about, parent, false)
        )

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AboutViewHolder(override val containerView: View) : AwwGalleryHolder<AboutInfo>(containerView) {

        override fun bind(model: AboutInfo) {
            content.text = content.resources.getString(model.name)
            containerView.setOnClickListener {
                when (model.action) {
                    is OpenAction.OpenLibs -> navController.navigate(R.id.open_libs)
                    is OpenAction.OpenUrl -> it.context.openWebPage(it.context.getString(model.action.url))
                }
            }
        }
    }
}
