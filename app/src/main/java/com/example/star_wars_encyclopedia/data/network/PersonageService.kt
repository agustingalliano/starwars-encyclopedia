package com.example.star_wars_encyclopedia.data.network

import com.example.star_wars_encyclopedia.data.model.PersonageResponse
import com.example.star_wars_encyclopedia.di.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PersonageService {

    private val retrofit = NetworkModule.provideRetrofit()

    suspend fun getPersonages(page: Int) :Response<PersonageResponse> {
        return withContext(Dispatchers.IO) {
            retrofit.create(PersonageApiClient::class.java).getAllPersonages(page) }
    }

}