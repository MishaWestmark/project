package com.westmark.usecaseexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.usecaseexample.data.UserRepositoryImpl
import com.westmark.usecaseexample.databinding.ActivityMainBinding
import com.westmark.usecaseexample.domain.models.SaveUserNameParams
import com.westmark.usecaseexample.domain.usecase.GetUserNameUseCase
import com.westmark.usecaseexample.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private val userRepository by lazy { UserRepositoryImpl(applicationContext) }
    private val getUserNameUseCase by lazy {GetUserNameUseCase(userRepository)}
    private val saveUserNameUseCase by lazy {SaveUserNameUseCase(userRepository)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.btnGetData.setOnClickListener {
            val userName = getUserNameUseCase.execute()
            binding.txtData.text = "Name = ${userName.firstName} ${userName.lastName}"
        }
        binding.btnSaveData.setOnClickListener {
            val text = binding.editTextData.text.toString()
            val params = SaveUserNameParams(name = text)
            val result = saveUserNameUseCase.execute(param = params)
            binding.txtData.text = "Save result = $result"
        }
    }
}