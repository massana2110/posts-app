package com.example.postsapp.views.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.postsapp.repositories.MainRepository

@Suppress("UNCHECKED_CAST")
class CommentViewModelFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory()  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentViewModel(mainRepository) as T
    }
}