package com.westmark.unsplash.presentation.ui.photoList.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentDetailPhotoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail_photo) {
    private val viewModel: DetailViewModel by viewModels()
    private val binding: FragmentDetailPhotoBinding by viewBinding()
    private val args: DetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        val shareLink =  "https://unsplash.com/photos/${args.id}"
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.shareButton -> {
                    val url = shareLink
                    if (url != null) {
                        //todo расшарить ссылку
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.getPhotoById(args.id)
            viewModel.photoByIdLiveData.observe(viewLifecycleOwner) { photo ->
                Log.d("photoDetail", photo.toString())
                with(binding) {
                    photo.tags.forEach {
                        val listOfTags = mutableListOf<String>()
                        listOfTags.add(it.title.toString())
                        textViewHashtag.text = listOfTags.joinToString(prefix = "#")
                    }
//                    textViewHashtag.text = photo.tags.joinToString()
                    textViewLikes.text = photo.likes.toString()
                    textViewProfileName.text = photo.user.name
                    textViewAccount.text = photo.user.userName
                    textViewMade.text = "Made wiht: ${photo.exif.made}"
                    textViewModel.text = "Model: ${photo.exif.model}"
                    textViewExposure.text = "Exposure: ${photo.exif.exposureTime}"
                    textViewAperture.text = "Aperture: ${photo.exif.aperture}"
                    textViewFocalLength.text = "Focal Length: ${photo.exif.focalLength}"
                    textViewIso.text = "ISO: ${photo.exif.iso}"
                    textViewInformation.text = "About @${photo.user.userName}: ${photo.user.bio}"

                }
                Glide.with(requireContext())
                    .load(photo.urls.urlRegular)
                    .override(Target.SIZE_ORIGINAL)
                    .into(binding.imageViewPhoto)
                Glide.with(requireContext())
                    .load(photo.user.profileImage.imageUrlSmall)
                    .circleCrop()
                    .into(binding.imageViewAccount)

            }
        }
    }

}