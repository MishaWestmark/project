package com.example.testappeff.screens.main.page2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testappeff.R
import com.example.testappeff.databinding.FragmentPage2Binding
import com.example.testappeff.screens.main.page2.adapter.ImageAdapter
import com.example.testappeff.utils.autoCleared
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Page2Fragment : Fragment(R.layout.fragment_page2) {
    private val binding: FragmentPage2Binding by viewBinding()
    private var imageAdapter: ImageAdapter by autoCleared()
    private val viewModel: Page2ViewModel by viewModels()
    var imageUrlList = listOf<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnClickListener {
            findNavController().navigate(Page2FragmentDirections.actionPage2FragmentToPage1Fragment())
        }
        viewModel.detailInfoLiveData.observe(viewLifecycleOwner) { item ->
            imageUrlList = item.imageUrls
            lifecycleScope.launch {
                delay(500)
                setupViewpager()
                setupIndicators()
                setupItemColors(item.listColors)
            }
            with(binding) {
                tvName.text = item.name
                tvDescription.text = item.description
                tvPrice.text = "$ ${item.price}"
                tvRating.text = item.rating
                tvReviews.text = "(${item.numberOfReviews})"
            }
        }
    }

    private fun setupViewpager() {
        imageAdapter = ImageAdapter(imageUrlList)
        with(binding.viewPager) {
            adapter = imageAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })
        }
    }

    private fun setupItemColors(listColors: List<String>) {
        val indicators = arrayOfNulls<MaterialCardView>(listColors.size)
        val params = LinearLayout.LayoutParams(100, 50)
        params.setMargins(50, 12, 0, 0)
        for (i in indicators.indices) {
            indicators[i] = MaterialCardView(requireContext())
            indicators[i]?.let { cardColor ->
                with(cardColor) {
                    layoutParams = params
                    if (listColors[i].equals("#FFFFFF", true)) {
                        strokeColor =
                            ContextCompat.getColor(requireContext(), R.color.text_color_gray)
                        strokeWidth = 4
                    }
                    setCardBackgroundColor(Color.parseColor(listColors[i]))
                    setPadding(4, 4, 4, 4)
                    radius = 36f
                }
                binding.colorContainer.addView(cardColor)
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(imageAdapter.itemCount)
        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i]?.let {
                Glide.with(requireContext())
                    .load(imageUrlList[i])
                    .transform(RoundedCorners(36))
                    .into(it)
                binding.indicatorsContainer.addView(it)
                it.setOnClickListener {
                    binding.viewPager.currentItem = i
                }
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val indicatorContainer = binding.indicatorsContainer
        val childCount = indicatorContainer.childCount
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                150, 70
            )
        layoutParams.setMargins(16, 0, 0, 0)
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.layoutParams = LinearLayout.LayoutParams(250, 150)
            } else {
                imageView.layoutParams = layoutParams
            }
        }
    }
}