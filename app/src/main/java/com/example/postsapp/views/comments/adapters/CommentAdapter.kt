package com.example.postsapp.views.comments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginLeft
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.R
import com.example.postsapp.databinding.CommentItemBinding
import com.example.postsapp.models.Comment

class CommentAdapter(
    private val comments: List<Comment>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(
        val commentItemBinding: CommentItemBinding
    ) : RecyclerView.ViewHolder(commentItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.comment_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.commentItemBinding.apply {
            comment = comments[position]
        }
    }

    override fun getItemCount(): Int = comments.size

}