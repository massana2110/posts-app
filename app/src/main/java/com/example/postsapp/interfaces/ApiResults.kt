package com.example.postsapp.interfaces

interface ApiResults {
    fun onStarted() // for displaying a progress bar or loader
    fun onSuccess() // Handling successful api response
    fun onFailure() // Handling errors api response
}