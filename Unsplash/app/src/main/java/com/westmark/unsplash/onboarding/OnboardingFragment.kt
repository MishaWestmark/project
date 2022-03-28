package com.westmark.unsplash.onboarding

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    private val binding: FragmentOnboardingBinding by viewBinding()
    private lateinit var adapterOnboard: OnboardingScreensAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnboardingScreens()
        setupIndicators()
        setCurrentIndicator(0)

    }

    private fun setOnboardingScreens() {
        val screens: List<OnboardingScreen> = listOf(
            OnboardingScreen(R.string.onboarding_text_1),
            OnboardingScreen(R.string.onboarding_text_2),
            OnboardingScreen(R.string.onboarding_text_3)
        )
        adapterOnboard = OnboardingScreensAdapter(screens)
        with(binding.viewPager) {
            adapter = adapterOnboard
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            binding.btnForward.setOnClickListener {
                if (currentItem + 1 < adapterOnboard.itemCount) {
                    currentItem += 1
                } else {
                    findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
                }
            }

        }
    }


    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(adapterOnboard.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val indicatorContainer = binding.indicatorsContainer
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}