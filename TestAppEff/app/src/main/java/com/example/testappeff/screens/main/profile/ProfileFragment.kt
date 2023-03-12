package com.example.testappeff.screens.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.testappeff.R
import com.example.testappeff.databinding.FragmentProfileBinding
import com.example.testappeff.screens.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private var imagePickerResultActivityLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { result ->
        Glide.with(requireContext())
            .load(result)
            .circleCrop()
            .into(binding.ivPhotoProfile)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvChangePhoto.setOnClickListener {
            imagePickerResultActivityLauncher.launch("image/*")
        }
        binding.cardLogOut.setOnClickListener {
            try {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMainActivity())
            } catch (e: Throwable) {
                Log.e("profileFrag", e.message.toString())
            }

        }
        Glide
            .with(this)
            .load(R.drawable.avatar)
            .circleCrop()
            .into(binding.ivPhotoProfile)
    }
}