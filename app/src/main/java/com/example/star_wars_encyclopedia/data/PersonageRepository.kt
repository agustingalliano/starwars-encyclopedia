package com.example.star_wars_encyclopedia.data

import android.content.Context
import com.example.star_wars_encyclopedia.data.database.entity.PersonageEntity
import com.example.star_wars_encyclopedia.data.model.PersonageResponse
import com.example.star_wars_encyclopedia.data.network.PersonageService
import com.example.star_wars_encyclopedia.di.RoomModule
import com.example.star_wars_encyclopedia.domain.model.Personage
import com.example.star_wars_encyclopedia.domain.model.toDomain
import retrofit2.Response

class PersonageRepository(applicationContext: Context) {

    private val personageService = PersonageService()
    private val personageDao = RoomModule.providePersonageDao(applicationContext)
    lateinit var personageEntity: PersonageEntity

    suspend fun getAllPersonagesFromApi(page:Int) : Response<PersonageResponse> {
//        val response = peopleService.getPeoples(page, pageTotal)
//        return response.map { it.toDomain() }.toMutableList()
        return personageService.getPersonages(page)

    }

    suspend fun getPersonageFromDatabase(name: String?) : Personage {
        personageEntity = personageDao.findById(name)
        return personageEntity.toDomain()
    }

    suspend fun clearPersonage() {
        personageDao.deleteAllPersonage()
    }

    suspend fun insertPersonage(personages: List<PersonageEntity>) {
        personageDao.insertAll(personages)
    }

    suspend fun getPersonagesFromDatabase() : List<Personage> {
        val response = personageDao.getAllQuotes()
        return response.map { it.toDomain() }
    }



}