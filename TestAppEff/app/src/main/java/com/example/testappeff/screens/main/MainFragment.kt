package com.example.testappeff.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testappeff.R
import com.example.testappeff.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            (childFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment).navController
        binding.bottomNavigationBar.setupWithNavController(navController)
    }

//    private fun visibilityNavElements(navController: NavController) {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.signInFragment2,
//                R.id.loginFragment -> binding.bottomNavigationBar.visibility = View.GONE
//                else -> binding.bottomNavigationBar.visibility = View.VISIBLE
//            }
//        }
//    }
}