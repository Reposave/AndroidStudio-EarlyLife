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
class ReportViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: List<Report>
    private val repository: ReportRepository

    // first executed when ReportViewModel is called
    init{
        val reportDao = ReportDatabase.getDatabase(application).reportDao()
        repository = ReportRepository(reportDao)
        readAllData = repository.readAllData
    }

    fun addReport(report: Report){
        // run this in a background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addReport(report)
        }
    }
    
}