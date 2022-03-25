package com.example.star_wars_encyclopedia.listeners

import android.os.Bundle
import com.example.star_wars_encyclopedia.domain.model.Personage

interface FragmentCallBack {
    fun onCallBack(personage: Personage)
}