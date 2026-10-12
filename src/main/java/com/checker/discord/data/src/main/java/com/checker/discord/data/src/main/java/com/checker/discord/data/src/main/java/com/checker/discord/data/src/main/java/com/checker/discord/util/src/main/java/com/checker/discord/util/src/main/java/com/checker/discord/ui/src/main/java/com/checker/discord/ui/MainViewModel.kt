package com.checker.discord.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.checker.discord.data.CheckerRepository
import com.checker.discord.data.UsernameResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CheckerRepository) : ViewModel() {
    val allResults: Flow<List<UsernameResult>> = repository.allResults

    fun checkUsername(username: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val isAvailable = repository.checkUsername(username)
                onResult(isAvailable)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
