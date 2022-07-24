package com.example.postsapp.views.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.postsapp.repositories.MainRepository

@Suppress("UNCHECKED_CAST")
class PostsViewModelFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(mainRepository) as T
    }

}