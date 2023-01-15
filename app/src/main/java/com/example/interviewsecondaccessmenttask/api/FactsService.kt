package com.example.interviewsecondaccessmenttask.api

import com.example.interviewsecondaccessmenttask.models.FactsData
import retrofit2.Response
import retrofit2.http.GET

interface FactsService{

    @GET("/fact")
    suspend fun getFacts(): Response<FactsData>
}