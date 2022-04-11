package com.westmark.usecaseexample.domain.usecase

import com.westmark.usecaseexample.domain.models.UserName
import com.westmark.usecaseexample.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {
    fun execute(): UserName {
        return userRepository.getName()
    }
}