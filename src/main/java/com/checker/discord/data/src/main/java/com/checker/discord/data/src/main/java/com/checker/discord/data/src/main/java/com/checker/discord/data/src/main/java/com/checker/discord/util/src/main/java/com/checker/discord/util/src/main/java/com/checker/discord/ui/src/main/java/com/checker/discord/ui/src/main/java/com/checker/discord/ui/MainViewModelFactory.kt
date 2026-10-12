package com.checker.discord.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.checker.discord.data.CheckerRepository

class MainViewModelFactory(private val repository: CheckerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
