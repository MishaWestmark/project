package com.westmark.mychatapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.mychatapp.R
import com.westmark.mychatapp.databinding.LoginFragmentBinding
import com.westmark.mychatapp.toast

class LoginFragment : Fragment(R.layout.login_fragment) {
    private val viewModel: LoginViewModel by viewModels()
    private val binding: LoginFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }


    private fun login() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        viewModel.login(email, password)
        viewModel.loginLiveData.observe(viewLifecycleOwner) { task ->
            task.addOnCompleteListener{
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_userListFragment)

                } else {
                    toast(requireContext(), "Incorrect login or password, Log in failed")
                }
            }
        }
    }
}