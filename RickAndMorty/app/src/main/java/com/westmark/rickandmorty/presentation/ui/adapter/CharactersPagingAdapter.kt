package com.westmark.rickandmorty.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.westmark.rickandmorty.R
import com.westmark.rickandmorty.databinding.ItemCharacterBinding
import com.westmark.rickandmorty.models.Response

class CharactersPagingAdapter(private val onItemClick: (id: String) -> Unit) :
    PagingDataAdapter<Response.Character, CharactersPagingAdapter.CharacterViewHolder>(
        CharactersPagingAdapter.CharactersDiffUtil()
    ) {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_character,
                parent, false
            ), onItemClick
        )
    }

    class CharacterViewHolder(
        view: View,
        private val onItemClick: (id: String) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCharacterBinding.bind(view)
        private var currentID: String? = null

        init {
            with(view) {
                setOnClickListener {
                    currentID?.let(onItemClick)
                }
            }
        }

        fun bind(
            character: Response.Character
        ) {
            currentID = character.id.toString()
            with(binding) {
                txtName.text = character.name
                txtSpecies.text = character.species
                txtGender.text = character.gender
            }
            Glide.with(itemView)
                .load(character.image)
                .into(binding.imageViewAvatar)
        }
    }

    class CharactersDiffUtil : DiffUtil.ItemCallback<Response.Character>() {
        override fun areItemsTheSame(
            oldItem: Response.Character,
            newItem: Response.Character
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Response.Character,
            newItem: Response.Character
        ): Boolean {
            return oldItem == newItem
        }

    }

}
