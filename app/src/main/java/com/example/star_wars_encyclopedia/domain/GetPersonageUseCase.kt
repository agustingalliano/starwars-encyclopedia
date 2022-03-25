package com.example.star_wars_encyclopedia.domain

import android.content.Context
import com.example.star_wars_encyclopedia.data.PersonageRepository
import com.example.star_wars_encyclopedia.data.database.entity.toEntity
import com.example.star_wars_encyclopedia.data.model.PersonageResponse
import com.example.star_wars_encyclopedia.domain.model.Personage
import com.example.star_wars_encyclopedia.domain.model.toDomain
import retrofit2.Response

class GetPersonageUseCase(applicationContext: Context) {

    private val repository: PersonageRepository = PersonageRepository(applicationContext)

    suspend operator fun invoke(page: Int): Response<PersonageResponse> {

        val response = repository.getAllPersonagesFromApi(page)

        val personages = response.body()?.results
        if(!personages.isNullOrEmpty() && page == 1) {
            repository.clearPersonage()
            repository.insertPersonage(personages.map { it.toDomain().toEntity() })
        } else if(!personages.isNullOrEmpty() && page != 1) {
            repository.insertPersonage(personages.map { it.toDomain().toEntity() })
        }

        return response
    }

    suspend fun getPersonage(name: String?): Personage {
        return repository.getPersonageFromDatabase(name)
    }

}