package com.example.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoginFragment()
    }

    private fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_for_fragment, LoginFragment())
            .commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        for (fragment in fragmentManager.fragments) {
            if (fragment.isVisible) {
                val childfragmentManager = fragment.childFragmentManager
                if (childfragmentManager.backStackEntryCount > 0) {
                    for (childfragnested in childfragmentManager.fragments) {
                        val childFmNestManager = childfragnested.fragmentManager
                        if (childfragnested.isVisible) {
                            childFmNestManager?.popBackStack()
                            return
                        }
                    }
                }
            }
        }
        super.onBackPressed()
    }
}