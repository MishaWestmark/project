package com.example.testappeff.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.Repository
import com.example.testappeff.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
) : BaseViewModel<LoginState>(LoginState()) {
    private var _isExist = MutableLiveData<Boolean>()
    val isExist: LiveData<Boolean> = _isExist


    suspend fun checkProfile(firstName: String, password: String) {
        repository.loginProfile(firstName, password)
            .collect {
                if (it == null) {
                    updateState { copy(isProfileExist = false) }
                } else {
                    updateState { copy(isProfileExist = true) }
                }
                Log.e("loginviewmodel", it.toString())
                Log.e("loginviewmodel", stateFlow.value.isProfileExist.toString())
                _isExist.value = stateFlow.value.isProfileExist
            }
    }
}