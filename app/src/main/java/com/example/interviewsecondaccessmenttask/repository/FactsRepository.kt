package com.example.interviewsecondaccessmenttask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.interviewsecondaccessmenttask.api.FactsService
import com.example.interviewsecondaccessmenttask.models.FactsData

class FactsRepository(private var factsService: FactsService) {

    private val factsLiveData = MutableLiveData<FactsData>()
    val facts: LiveData<FactsData>
        get() = factsLiveData

    suspend fun getFacts() {

        val result = factsService.getFacts()

        if (result.body() != null) {

            factsLiveData.postValue(result.body())
        }
    }

}