package com.example.interviewsecondaccessmenttask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.interviewsecondaccessmenttask.models.FactsData
import com.example.interviewsecondaccessmenttask.repository.FactsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: FactsRepository) : ViewModel() {

    val facts: LiveData<FactsData> = repository.facts
    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    fun getFacts() {
        viewModelScope.launch {
            repository.getFacts()
        }
    }
}
