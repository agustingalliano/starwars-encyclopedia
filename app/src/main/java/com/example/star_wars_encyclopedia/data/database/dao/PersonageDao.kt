package com.example.star_wars_encyclopedia.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.star_wars_encyclopedia.data.database.entity.PersonageEntity

@Dao
interface PersonageDao {

    @Query("SELECT * FROM personage_table WHERE name=:name")
    suspend fun findById(name: String?) : PersonageEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<PersonageEntity>)

    @Query("DELETE FROM personage_table")
    suspend fun deleteAllPersonage()

    @Query("SELECT * FROM personage_table")
    suspend fun getAllQuotes(): List<PersonageEntity>
}