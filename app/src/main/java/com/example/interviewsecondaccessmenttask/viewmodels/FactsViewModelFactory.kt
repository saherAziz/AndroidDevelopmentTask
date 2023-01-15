package com.example.interviewsecondaccessmenttask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interviewsecondaccessmenttask.repository.FactsRepository

@Suppress("UNCHECKED_CAST")
class FactsViewModelFactory(private var repository: FactsRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}