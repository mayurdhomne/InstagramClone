package com.app.instagramclone.adapter

import androidx.recyclerview.widget.DiffUtil
import com.app.instagramclone.model.Post

class PostDiffCallback(
    private val oldList: List<Post>,
    private val newList: List<Post>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
