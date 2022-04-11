package com.westmark.usecaseexample.domain.usecase

import com.westmark.usecaseexample.domain.models.SaveUserNameParams
import com.westmark.usecaseexample.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {
    fun execute(param: SaveUserNameParams): Boolean {
        val result = userRepository.saveName(params = param)
        return result
    }
}