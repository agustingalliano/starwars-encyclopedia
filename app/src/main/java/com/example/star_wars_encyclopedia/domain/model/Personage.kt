package com.example.star_wars_encyclopedia.domain.model

import com.example.star_wars_encyclopedia.data.database.entity.PersonageEntity
import com.example.star_wars_encyclopedia.data.model.PersonageModel

data class Personage (val name:String, val height: String, val mass: String, val hair_color: String, val skin_color: String, val eye_color: String, val gender: String)

fun PersonageModel.toDomain() = Personage(name, height, mass, hair_color, skin_color, eye_color, gender)
fun PersonageEntity.toDomain() = Personage(name, height, mass, hair_color, skin_color, eye_color, gender)