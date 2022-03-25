package com.example.star_wars_encyclopedia.data.network

import com.example.star_wars_encyclopedia.data.model.PersonageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonageApiClient {

    @GET("/api/people")
    suspend fun getAllPersonages(@Query("page") page: Int=1): Response<PersonageResponse>
}