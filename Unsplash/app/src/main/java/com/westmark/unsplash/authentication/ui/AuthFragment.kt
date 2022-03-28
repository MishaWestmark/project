package com.westmark.unsplash.authentication.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentAuthBinding
import com.westmark.unsplash.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val viewModel: AuthViewModel by viewModels()
    private val binding: FragmentAuthBinding by viewBinding()
    private val authResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val tokenExchangeRequest = result.data?.let {
                    AuthorizationResponse.fromIntent(it)
                        ?.createTokenExchangeRequest()
                }
                val exception = AuthorizationException.fromIntent(result.data)
                when {
                    tokenExchangeRequest != null && exception == null ->
                        viewModel.onAuthCodeReceived(tokenExchangeRequest)
                    exception != null -> viewModel.onAuthCodeFailed(exception)
                }
            } else {
                toast(R.string.call_canceled)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        binding.loginButton.setOnClickListener { viewModel.openLoginPage() }
        viewModel.loadingLiveData.observe(viewLifecycleOwner, ::updateIsLoading)
        viewModel.openAuthPageLiveData.observe(viewLifecycleOwner, ::openAuthPage)
        viewModel.toastLiveData.observe(viewLifecycleOwner, ::toast)
        viewModel.authSuccessLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToMainFragment())
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        binding.loginButton.isVisible = !isLoading
        binding.loginProgress.isVisible = isLoading
    }

    private fun openAuthPage(intent: Intent) {
        authResult.launch(intent)
    }
}