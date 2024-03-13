package com.wigroup.jetpackcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isFollowing = MutableLiveData<Boolean>()
    val isFollowing: LiveData<Boolean> = _isFollowing

    fun changeFollowingState() {
        val wasFollowing = _isFollowing.value ?: false
        _isFollowing.value = !wasFollowing
    }
}