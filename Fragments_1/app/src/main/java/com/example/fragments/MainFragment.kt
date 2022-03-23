package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainFragment : Fragment(R.layout.fragment_main), ItemSelect {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        showListFragment()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun showListFragment() {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (!isTablet) {
            childFragmentManager.beginTransaction()
                .add(R.id.fragment_main_container, ListFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onItemSelected(text: String) {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (!isTablet) {
            childFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_main_container, DetailFragment.newInstance(text))
                .addToBackStack(null)
                .commit()
        } else {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_main_cont, DetailFragment.newInstance(text))
                .addToBackStack(null)
                .commit()
        }
    }
}