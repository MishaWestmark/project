package com.westmark.unsplash.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.westmark.unsplash.presentation.data.PhotoEntity
import com.westmark.unsplash.presentation.data.UserEntity
import com.westmark.unsplash.presentation.ui.profile.data.RepositoryProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: RepositoryProfile) :
    ViewModel() {
    private val userInfoMutableLiveData = MutableLiveData<UserEntity>()
    val userInfoLiveData: LiveData<UserEntity> = userInfoMutableLiveData
    private val userLikedPhotosMutableLiveData = MutableLiveData<PagingData<PhotoEntity>>()
    val userLikedPhotosLiveData: LiveData<PagingData<PhotoEntity>> = userLikedPhotosMutableLiveData
    init {
        viewModelScope.launch {
            userInfoMutableLiveData.postValue(repository.getUserInfo())
        }
    }


}