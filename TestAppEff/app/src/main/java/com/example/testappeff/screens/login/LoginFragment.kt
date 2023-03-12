package com.example.testappeff.screens.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testappeff.R
import com.example.testappeff.afterTextChanged
import com.example.testappeff.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()
    private var textFirstName = ""
    private var textPassword = ""
    private var isExist = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            edFirstName.afterTextChanged { textFirstName = it }
            edPassword.afterTextChanged { textPassword = it }
        }
        binding.btnLogin.setOnClickListener { view ->
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.checkProfile(textFirstName, textPassword)
                }
            }
            viewModel.isExist.observe(viewLifecycleOwner) {
                if (it) {
                    try {
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//                        parentFragmentManager.beginTransaction()
//                            .replace(R.id.container_view, MainFragment())
//                            .addToBackStack(null)
//                            .commit()
                    } catch (e: Throwable) {
                        Log.e("loginFragment", e.message.toString())
                    }


                } else {
                    Toast.makeText(context, "profile not exist", Toast.LENGTH_LONG).show()
                }
            }
        }
        Log.e("loginFragment", "click login")
        Log.e("loginFragment", "$textFirstName")
        Log.e("loginFragment", "$textPassword")
    }
}

