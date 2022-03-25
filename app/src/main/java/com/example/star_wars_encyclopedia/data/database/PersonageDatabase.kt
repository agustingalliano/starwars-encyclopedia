package com.example.star_wars_encyclopedia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.star_wars_encyclopedia.data.database.dao.PersonageDao
import com.example.star_wars_encyclopedia.data.database.entity.PersonageEntity

@Database(entities = [PersonageEntity::class], version = 1)
abstract class PersonageDatabase: RoomDatabase() {

    abstract fun getPersonageDao(): PersonageDao

}