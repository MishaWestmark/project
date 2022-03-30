package com.westmark.unsplash.presentation.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentProfileBinding
import com.westmark.unsplash.presentation.ui.photoList.adapter.autoCleared
import com.westmark.unsplash.presentation.ui.profile.viewPager.adapter.ProfileViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModels()
    private var adapterViewPager: ProfileViewPagerAdapter by autoCleared()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        adapterViewPager = ProfileViewPagerAdapter(this)
        binding.viewPager.adapter = adapterViewPager

    }


    private fun bindViewModel() {
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) { user ->
            with(binding) {
                textViewName.text = user.name
                textViewAccount.text = user.userName
                textViewBio.text = user.bio
                textViewDownloads.text = user.downloads.toString()
                textViewEmail.text = user.email
                textViewLocation.text = user.location
            }
            Glide.with(requireContext())
                .load(user.urlImageProfile.urlMedium)
                .circleCrop()
                .into(binding.imageViewProfile)

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Photos ${user.totalPhotos}"
                    1 -> tab.text = "Liked photos ${user.totalLikes}"
                    2 -> tab.text = "Collections ${user.totalCollections}"
                }
            }.attach()
        }
    }
}