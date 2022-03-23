package com.westmark.mychatapp.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.mychatapp.R
import com.westmark.mychatapp.databinding.SignUpFragmentBinding
import com.westmark.mychatapp.toast
import com.westmark.mychatapp.ui.userList.UserListFragment

class SignUpFragment : Fragment(R.layout.sign_up_fragment) {
    private val binding: SignUpFragmentBinding by viewBinding()
    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        viewModel.signUp(name, email, password)
        viewModel.signUpLiveData.observe(viewLifecycleOwner) {task->
            task.addOnCompleteListener {
                if (task.isSuccessful) {
                    viewModel.adduserToDatabase(name, email)
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToUserListFragment())
                } else {
                    toast(requireContext(), "Authentication failed")
                }
            }
        }
    }
}