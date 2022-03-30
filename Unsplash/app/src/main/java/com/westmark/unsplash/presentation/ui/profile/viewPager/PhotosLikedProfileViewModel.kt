package com.westmark.unsplash.presentation.ui.profile.viewPager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.westmark.unsplash.presentation.data.PhotoEntity
import com.westmark.unsplash.presentation.ui.profile.data.RepositoryProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosLikedProfileViewModel @Inject constructor(private val repository: RepositoryProfile) :
    ViewModel() {
    private val userLikedMutableStateFlow =
        MutableStateFlow<PagingData<PhotoEntity>>(PagingData.empty())
    val userLikedStateFlow: StateFlow<PagingData<PhotoEntity>> = userLikedMutableStateFlow

    init {
        viewModelScope.launch {
            getUserLikedPhoto(repository.getUserInfo().userName)
        }

    }

    private fun getUserLikedPhoto(userName: String) {
        viewModelScope.launch {
            repository.getUserLikedPhotos(userName)
                .collectLatest {
                    userLikedMutableStateFlow.value = it
                }
        }
    }
}