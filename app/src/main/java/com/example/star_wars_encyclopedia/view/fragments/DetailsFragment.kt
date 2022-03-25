package com.example.star_wars_encyclopedia.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.star_wars_encyclopedia.databinding.FragmentDetailsBinding
import com.example.star_wars_encyclopedia.domain.model.Personage

class DetailsFragment() : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var personage: Personage


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

            ("Name: " + personage.name).also { binding.name.text = it }
            ("Height: " + personage.height).also { binding.height.text = it }
            ("Mass: " + personage.mass).also { binding.mass.text = it }
            ("Hair color: " + personage.hair_color).also { binding.hairColor.text = it }
            ("Skin Color: " + personage.skin_color).also { binding.skinColor.text = it }
            ("Eye color: " + personage.eye_color).also { binding.eyeColor.text = it }
            ("Gender: " + personage.gender).also { binding.gender.text = it }

        return binding.root
    }

    fun setPersonage(personage: Personage) {
        this.personage = personage
    }



}