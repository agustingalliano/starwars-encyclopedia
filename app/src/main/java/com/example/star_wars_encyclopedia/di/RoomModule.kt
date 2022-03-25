package com.example.star_wars_encyclopedia.di

import android.content.Context
import androidx.room.Room
import com.example.star_wars_encyclopedia.data.database.PersonageDatabase

object RoomModule {

    private const val PERSONAGE_DATABASE_NAME = "personage_database"

    private fun provideRoom(context: Context) = Room.databaseBuilder(context, PersonageDatabase::class.java, PERSONAGE_DATABASE_NAME).build()

    fun providePersonageDao(context: Context) = provideRoom(context).getPersonageDao()

}