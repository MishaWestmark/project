package com.westmark.unsplash.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.ItemOnboardingBinding

class OnboardingScreensAdapter(private val screens: List<OnboardingScreen>) :
    RecyclerView.Adapter<OnboardingScreensAdapter.OnboardingScreensViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingScreensViewHolder {
        return OnboardingScreensViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnboardingScreensViewHolder, position: Int) {
        holder.bind(screens[position])
    }

    override fun getItemCount(): Int {
        return screens.size
    }

    class OnboardingScreensViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemOnboardingBinding.bind(view)
        fun bind(
            screen: OnboardingScreen
        ) {
            binding.textViewOnboardText.setText(screen.text)
        }
    }
}