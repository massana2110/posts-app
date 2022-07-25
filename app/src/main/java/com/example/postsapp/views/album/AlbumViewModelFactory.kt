package com.example.postsapp.views.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.postsapp.repositories.MainRepository
import com.example.postsapp.views.comments.CommentViewModel

@Suppress("UNCHECKED_CAST")
class AlbumViewModelFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(mainRepository) as T
    }

}