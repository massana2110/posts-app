package com.example.postsapp.api

import com.example.postsapp.models.Comment
import com.example.postsapp.models.Post
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

// Build Moshi object that retrofit will be using to map response
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit builder to build a retrofit object using moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// API Endpoints definitions
interface PostsApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(
        @Path("id") id: Int
    ) : Response<List<Comment>>


}

// Singleton instance of retrofit
object PostsApi {
    val retrofitService: PostsApiService by lazy { retrofit.create(PostsApiService::class.java) }
}