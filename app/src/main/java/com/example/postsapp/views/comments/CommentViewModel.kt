package com.example.postsapp.views.comments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.models.Comment
import com.example.postsapp.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    fun clear() {
        _comments.value = emptyList()
        Log.d("CommentViewModel", "Cleared Comment View Model")
    }

    fun getCommentsAction(id: Int) {
        viewModelScope.launch {
            _comments.value = getCommentsByPostWithContext(id)
        }
    }

    private suspend fun getCommentsByPostWithContext(id: Int) : List<Comment> {
        return withContext(Dispatchers.IO) {
            mainRepository.getAllCommentsByPost(id)
        }
    }
}