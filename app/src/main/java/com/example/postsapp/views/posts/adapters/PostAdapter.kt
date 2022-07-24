package com.example.postsapp.views.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.R
import com.example.postsapp.databinding.PostItemBinding
import com.example.postsapp.models.Post

class PostAdapter(
    private val posts: List<Post>,
    private val listener: PostItemClickListener
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(
        val postItemBinding: PostItemBinding
    ) : RecyclerView.ViewHolder(postItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.postItemBinding.post = posts[position]
        holder.postItemBinding.albumPostBtn.setOnClickListener {
            posts[position].id?.let { id ->
                listener.onPostItemClick(holder.postItemBinding.albumPostBtn,
                    id
                )
            }
        }
        holder.postItemBinding.commentsPostBtn.setOnClickListener {
            posts[position].id?.let { id ->
                listener.onPostItemClick(holder.postItemBinding.commentsPostBtn,
                    id
                )
            }
        }
    }

    override fun getItemCount(): Int = posts.size

}