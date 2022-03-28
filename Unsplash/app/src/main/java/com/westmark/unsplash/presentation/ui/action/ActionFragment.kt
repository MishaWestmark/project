package com.westmark.unsplash.presentation.ui.action

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.westmark.unsplash.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActionFragment : Fragment(R.layout.fragment_action) {
    private val viewModel: ViewModelAction by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isFirstEntry.observe(viewLifecycleOwner) { isFirstEntry ->
            when (isFirstEntry) {
                true -> {
                    if (viewModel.containsAccessToken()) {
                        findNavController().navigate(R.id.action_actionFragment_to_mainFragment)
                    } else {
                        findNavController().navigate(R.id.action_actionFragment_to_authFragment)
                    }
                }
                else -> {
                    findNavController().navigate(R.id.action_actionFragment_to_onboardingFragment)
                    viewModel.addFlagAfterFirstEntry()
                }
            }

        }
    }
}