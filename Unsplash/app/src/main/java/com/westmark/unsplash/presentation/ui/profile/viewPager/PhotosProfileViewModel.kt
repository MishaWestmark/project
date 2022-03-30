package com.westmark.unsplash.presentation.ui.profile.viewPager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.westmark.unsplash.presentation.data.PhotoEntity
import com.westmark.unsplash.presentation.ui.profile.data.RepositoryProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosProfileViewModel @Inject constructor(private val repository: RepositoryProfile) :
    ViewModel() {
    private val userPhotoMutableStateFlow =
        MutableStateFlow<PagingData<PhotoEntity>>(PagingData.empty())
    val userPhotoStateFlow: StateFlow<PagingData<PhotoEntity>> = userPhotoMutableStateFlow
    init {
        viewModelScope.launch {
           getUserPhotos(repository.getUserInfo().userName)
        }

    }
    private fun getUserPhotos(userName: String) {
        viewModelScope.launch {
            repository.getUserPhotos(userName)
                .collect {
                    userPhotoMutableStateFlow.value = it
                }
        }
    }
}