package com.westmark.rickandmorty.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.rickandmorty.R
import com.westmark.rickandmorty.databinding.FragmentCharactersBinding
import com.westmark.rickandmorty.presentation.ui.adapter.CharactersPagingAdapter
import com.westmark.rickandmorty.presentation.ui.adapter.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private val binding: FragmentCharactersBinding by viewBinding()
    private val viewModel: CharactersViewModel by viewModels()
    private var adapterCharacters: CharactersPagingAdapter by autoCleared()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        adapterCharacters = CharactersPagingAdapter { id ->
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToDetailFragment(
                    id = id
                )
            )
        }
        with(binding.recyclerViewCharacters) {
            adapter = adapterCharacters
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.charactersStateFlow.collectLatest {
                adapterCharacters.submitData(it)
            }
        }
    }
}