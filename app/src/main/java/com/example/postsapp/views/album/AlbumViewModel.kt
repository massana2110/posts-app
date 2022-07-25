package com.example.postsapp.views.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.models.Comment
import com.example.postsapp.models.Image
import com.example.postsapp.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    
    private var _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>>
        get() = _images
    
    fun clear() {
        _images.value = emptyList()
        Log.d("ImageViewModel", "Cleared Image View Model")
    }

    fun getPhotosAction(id: Int) {
        viewModelScope.launch {
            _images.value = getPhotosByPostWithContext(id)
        }
    }

    private suspend fun getPhotosByPostWithContext(id: Int) : List<Image> {
        return withContext(Dispatchers.IO) {
            mainRepository.getAllPhotosByPost(id)
        }
    }
    
}