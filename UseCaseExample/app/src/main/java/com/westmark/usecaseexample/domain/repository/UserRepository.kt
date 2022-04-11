package com.westmark.usecaseexample.domain.repository

import com.westmark.usecaseexample.domain.models.SaveUserNameParams
import com.westmark.usecaseexample.domain.models.UserName

interface UserRepository {
    fun saveName(params: SaveUserNameParams): Boolean
    fun getName(): UserName
}