package com.nicholasdoglio.eyebleach.ui.about.libs

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.nicholasdoglio.data.model.about.LibsItem

class LibsAdapter : ListAdapter<LibsItem, LibsAdapter.LibsViewHolder>(DIFF) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibsViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: LibsViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class LibsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<LibsItem>() {
            override fun areItemsTheSame(oldItem: LibsItem, newItem: LibsItem): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun areContentsTheSame(oldItem: LibsItem, newItem: LibsItem): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}