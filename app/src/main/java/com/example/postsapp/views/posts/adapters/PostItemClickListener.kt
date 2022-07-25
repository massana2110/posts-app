package com.example.postsapp.views.posts.adapters

import android.view.View

interface PostItemClickListener {
    fun onPostCommentItemClick(view: View, id: Int)
    fun onPostAlbumItemClick(view: View, id: Int)
}