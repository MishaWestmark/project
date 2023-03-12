package com.example.testappeff.screens.signIn

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testappeff.R
import com.example.testappeff.afterTextChanged
import com.example.testappeff.databinding.FragmentSigninBinding
import com.example.testappeff.isEmailValid
import com.example.testappeff.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_signin) {

    private val binding: FragmentSigninBinding by viewBinding()
    private val viewModel: SignInViewModel by viewModels()
    private var textEmail = ""
    private var textFirstName = ""
    private var textLastName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_loginFragment)
        }
        with(binding) {
            edFirstName.afterTextChanged { textFirstName = it }
            edLastName.afterTextChanged { textLastName = it }
            edEmail.afterTextChanged { textEmail = it }
        }
        binding.btnSignin.setOnClickListener {
            with(binding) {
                if (edFirstName.text!!.isEmpty()) {
                    edFirstName.error = "empty"
                } else if (edLastName.text!!.isEmpty()) {
                    edLastName.error = "empty"
                } else if (edEmail.text!!.isEmpty()) {
                    edEmail.error = "empty"
                } else {
                    if (edEmail.isEmailValid()) {
                        lifecycleScope.launch {
                            viewModel.saveNewProfile(textFirstName, textLastName, textEmail)
                        }
                        viewModel.isExistLive.observe(viewLifecycleOwner) {
                            if (it) {
                                try {
                                    findNavController().safeNavigate(R.id.action_signInFragment_to_mainFragment)
//                                    parentFragmentManager.beginTransaction()
//                                    .replace(R.id.container_view, MainFragment())
//                                    .addToBackStack(null)
//                                    .commit()
                                } catch (e: Throwable) {
                                    Log.e("signInFragment", e.message.toString())
                                }

                            } else {
                                Toast.makeText(context, "Profile exist", Toast.LENGTH_LONG).show()
                            }
                        }

                    } else {
                        edEmail.error = "incorrect format email"
                    }
                }
            }
        }
    }
}