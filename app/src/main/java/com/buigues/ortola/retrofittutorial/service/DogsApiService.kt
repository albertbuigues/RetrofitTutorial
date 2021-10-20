package com.buigues.ortola.retrofittutorial.service

import com.buigues.ortola.retrofittutorial.model.DogReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogsApiService
{
    @GET
    suspend fun getDogsByBreed(@Url url: String): Response<DogReponse>
}