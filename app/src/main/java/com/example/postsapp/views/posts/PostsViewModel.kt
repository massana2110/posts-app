package com.example.postsapp.views.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.interfaces.ApiResults
import com.example.postsapp.models.Post
import com.example.postsapp.repositories.MainRepository
import com.example.postsapp.utils.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    init {
        Log.d("PostViewModel", "Init post view model")
        getPostsAction()
    }

    override fun onCleared() {
        super.onCleared()
        _posts.value = emptyList()
        Log.d("PostViewModel", "Cleared Posts View Model")
    }

    private fun getPostsAction() {
        viewModelScope.launch {
            _posts.value = getPostsWithContext()
        }
    }

    private suspend fun getPostsWithContext(): List<Post> {
        return withContext(Dispatchers.IO) {
            mainRepository.getAllPosts()
        }
    }

}