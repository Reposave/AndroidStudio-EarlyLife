package com.example.earlylife.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Where we process queries from the Dao
 * Provides data to the UI and survives configuration changes
 * Acts as communication center between repository and UI
 */
class UserViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    // first executed when UserViewModel is called
    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        // run this in a background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}