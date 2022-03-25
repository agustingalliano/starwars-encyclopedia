package com.example.star_wars_encyclopedia.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.star_wars_encyclopedia.domain.model.Personage

@Entity(tableName = "personage_table")
data class PersonageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: String,
    @ColumnInfo(name = "mass") val mass: String,
    @ColumnInfo(name = "hair_color") val hair_color: String,
    @ColumnInfo(name = "skin_color") val skin_color: String,
    @ColumnInfo(name = "eye_color") val eye_color: String,
    @ColumnInfo(name = "gender") val gender: String
)

fun Personage.toEntity() = PersonageEntity(name = name, height = height, mass = mass, hair_color = hair_color, skin_color = skin_color, eye_color = eye_color, gender = gender)