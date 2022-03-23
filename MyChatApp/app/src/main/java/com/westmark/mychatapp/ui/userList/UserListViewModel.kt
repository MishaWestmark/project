package com.westmark.mychatapp.ui.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.westmark.mychatapp.data.Repository
import com.westmark.mychatapp.data.User

class UserListViewModel : ViewModel() {
    private val repository = Repository()
    private val userMutableLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>>
        get() = userMutableLiveData

    fun getUsersList() {
        repository.getUsers(userMutableLiveData)
    }
}