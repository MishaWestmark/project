package com.westmark.unsplash.presentation.ui.profile.viewPager.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.westmark.unsplash.presentation.ui.profile.viewPager.CollectionsProfileFragment
import com.westmark.unsplash.presentation.ui.profile.viewPager.PhotosLikedProfileFragment
import com.westmark.unsplash.presentation.ui.profile.viewPager.PhotosProfileFragment

class ProfileViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var screen: Fragment? = null
        when (position) {
            0 -> {
                screen = PhotosProfileFragment()
            }
            1 -> {
                screen = PhotosLikedProfileFragment()
            }
            2 -> {
                screen = CollectionsProfileFragment()
            }
        }
        return screen!!
    }
}