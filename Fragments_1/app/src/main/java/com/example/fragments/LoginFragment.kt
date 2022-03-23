package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        binding.buttonLogin.apply {
            showMainFragment()
        }

    }

    private fun showMainFragment() {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (!isTablet) {
            activity?.supportFragmentManager?.beginTransaction()
//            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
                ?.replace(R.id.container_for_fragment, MainFragment())
                ?.commit()
        } else {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container_for_fragment, MainFragment())
                ?.commit()
        }
    }
}