package com.example.star_wars_encyclopedia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.star_wars_encyclopedia.R
import com.example.star_wars_encyclopedia.databinding.ActivityMainBinding
import com.example.star_wars_encyclopedia.domain.model.Personage
import com.example.star_wars_encyclopedia.listeners.FragmentCallBack
import com.example.star_wars_encyclopedia.view.fragments.DetailsFragment
import com.example.star_wars_encyclopedia.view.fragments.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listFragment = ListFragment()
    val detailsFragment = DetailsFragment()
    var firstReleasedFragment : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, listFragment)
            if(firstReleasedFragment) {
               firstReleasedFragment = false
            } else {
                addToBackStack(null)
            }
            commit()
        }

        listFragment.setFragmentCallBack1(object: FragmentCallBack {
            override fun onCallBack(personage: Personage) {
                supportFragmentManager.beginTransaction().apply {
                    detailsFragment.setPersonage(personage)
                    replace(R.id.container, detailsFragment)
                    addToBackStack(null)
                    commit()
                }
            }

        })


    }


}