package com.westmark.rickandmorty.presentation.ui.detailInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.westmark.rickandmorty.R
import com.westmark.rickandmorty.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding: FragmentDetailBinding by viewBinding()
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun bindViewModel() {
        viewModel.getDetailCharacter(args.id)
        viewModel.detailInfoLiveData.observe(viewLifecycleOwner) { character ->
            with(binding) {
                txtName.text = "Name: ${character.name}"
                txtSpecies.text = "Species: ${character.species}"
                txtStatus.text = "Status: ${character.status}"
                txtGender.text = "Gender: ${character.gender}"
                txtLocation.text = "Location: ${character.location.name}"
                txtEpisodes.text = "Number of Episodes: ${character.episode.size}"
            }
            Glide.with(requireContext())
                .load(character.image)
                .into(binding.imageViewAvatar)
        }
    }
}