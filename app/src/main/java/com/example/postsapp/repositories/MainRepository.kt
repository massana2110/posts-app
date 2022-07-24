package com.example.postsapp.repositories

import com.example.postsapp.api.PostsApi
import com.example.postsapp.api.SafeApiRequest
import com.example.postsapp.models.Post

class MainRepository : SafeApiRequest() {

    suspend fun getAllPosts() : List<Post> {
        return apiRequest {
            PostsApi.retrofitService.getPosts()
        }
    }

}