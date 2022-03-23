package com.westmark.mychatapp.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.westmark.mychatapp.data.Repository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    private val loginMutableLiveData = MutableLiveData<Task<AuthResult>>()
    val loginLiveData: LiveData<Task<AuthResult>>
        get() = loginMutableLiveData

    fun login(email: String, password: String){
        loginMutableLiveData.postValue(repository.login(email, password))
    }
}