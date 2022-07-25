package com.example.postsapp.repositories

import com.example.postsapp.api.PostsApi
import com.example.postsapp.api.SafeApiRequest
import com.example.postsapp.models.Comment
import com.example.postsapp.models.Post

/**
 * Author: David Massana [david.massana.10@gmail.com]
 * Creation Date: July 24th, 2022
 * Description: Main Repository class where its defined the data sources such as API calls
 * database operations, etc.
 */
class MainRepository : SafeApiRequest() {

    suspend fun getAllPosts() : List<Post> {
        return apiRequest { PostsApi.retrofitService.getPosts() }
    }

    suspend fun getAllCommentsByPost(id: Int) : List<Comment> {
        return apiRequest { PostsApi.retrofitService.getCommentsByPostId(id) }
    }

}