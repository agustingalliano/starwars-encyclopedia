package com.example.star_wars_encyclopedia.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonageModel(@SerializedName("name") @Expose val name:String,
                          @SerializedName("height") @Expose val height: String,
                          @SerializedName("mass") @Expose val mass: String,
                          @SerializedName("hair_color") @Expose val hair_color:String,
                          @SerializedName("skin_color") @Expose val skin_color: String,
                          @SerializedName("eye_color") @Expose val eye_color: String,
                          @SerializedName("gender") @Expose val gender: String)