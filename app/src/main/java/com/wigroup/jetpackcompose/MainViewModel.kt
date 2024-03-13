package com.wigroup.jetpackcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val list = mutableListOf<InstagramModel>().apply {
        repeat(500) {
            add(
                InstagramModel(
                    id = it,
                    title = "Title: $it",
                    isFollowed = Random.nextBoolean(),
                )
            )
        }
    }

    private val _models = MutableLiveData<List<InstagramModel>>(list)
    val models: LiveData<List<InstagramModel>> = _models

    fun changeFollowingState(model: InstagramModel) {
        val copyList = _models.value?.toMutableList() ?: mutableListOf()
        copyList.replaceAll {
            if (it == model) it.copy(isFollowed = !it.isFollowed) else it
        }
        _models.value = copyList
    }
}