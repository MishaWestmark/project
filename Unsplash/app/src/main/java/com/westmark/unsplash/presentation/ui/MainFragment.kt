package com.westmark.unsplash.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost =
            childFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)


//
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.container_fragment, PhotoListFragment()).commit()
//        binding.bottomNavigation.setOnItemSelectedListener {
//            var fragment: Fragment? = null
//            when (it.itemId) {
//                R.id.photoListFragment -> {
//                    fragment = PhotoListFragment()
//                }
//                R.id.collectionsFragment -> {
//                    fragment = CollectionsFragment()
//                }
//                R.id.profileFragment -> {
//                    fragment = ProfileFragment()
//                }
//            }
//            parentFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment!!)
//                .commit()
//            return@setOnItemSelectedListener true
//        }
    }
}