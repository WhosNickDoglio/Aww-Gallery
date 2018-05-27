package com.nicholasdoglio.eyebleach.ui.about.libs

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nicholasdoglio.data.model.about.LibsItem

class LibsAdapter : ListAdapter<LibsItem, LibsAdapter.LibsViewHolder>(DIFF) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibsViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: LibsViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class LibsViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    }

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