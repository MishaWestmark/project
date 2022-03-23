package com.westmark.mychatapp.ui.signUp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.westmark.mychatapp.data.Repository

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    private val signUpMutableLiveData = MutableLiveData<Task<AuthResult>>()
    val signUpLiveData: LiveData<Task<AuthResult>>
        get() = signUpMutableLiveData

    fun signUp(name: String, email: String, password: String){
        signUpMutableLiveData.postValue(repository.signUp(name, email, password))
    }
    fun adduserToDatabase(name: String, email: String){
        repository.addUserToDatabase(name, email)
    }
}