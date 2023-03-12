package com.example.testappeff.screens.signIn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testappeff.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: RepositorySignIn
) : BaseViewModel<SignInState>(SignInState()) {

    private var _isExistLive = MutableLiveData<Boolean>()
    val isExistLive: LiveData<Boolean> = _isExistLive


    suspend fun saveNewProfile(firstName: String, lastName: String, email: String) {
        repository.checkProfileExist(firstName, lastName, email).collect {
            if (it == null) {
                updateState { copy(isProfile = true) }
                repository.saveNewProfile(firstName, lastName, email)
            } else {
                updateState { copy(isProfile = false) }
            }
            _isExistLive.value = stateFlow.value.isProfile
            Log.e("loginviewmodel", it.toString())
            Log.e("loginviewmodel", stateFlow.value.isProfile.toString())
        }

//            try {
//                launch {
//                repository.checkProfileExist(state.firstName, state.lastName, state.email).collect()
//                }
//            } catch (e: Throwable) {
//                updateState { copy(isProfileExist = false) }
//                Log.e("viewmodelSign", e.message.toString())
//            }
//            if (!stateFlow.value.isProfileExist) {
//                repository.saveNewProfile(state.firstName, state.lastName, state.email)
//            }

    }

//    fun updateProfileState(firstName: String, lastName: String, email: String) {
//        updateState { copy(firstName = firstName, lastName = lastName, email = email) }
//    }
}