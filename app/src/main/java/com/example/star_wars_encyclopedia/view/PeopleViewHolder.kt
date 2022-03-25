package com.example.star_wars_encyclopedia.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.star_wars_encyclopedia.databinding.ItemPersonageBinding
import com.example.star_wars_encyclopedia.domain.model.Personage

class PeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPersonageBinding.bind(view)

    fun render(personage: Personage) {
        binding.name.text = personage.name
    }

}
