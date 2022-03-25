package com.example.star_wars_encyclopedia.data.model

import com.google.gson.annotations.SerializedName

data class PersonageResponse(@SerializedName("results") val results: MutableList<PersonageModel>)