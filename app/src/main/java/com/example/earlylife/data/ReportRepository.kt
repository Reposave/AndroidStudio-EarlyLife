package com.example.earlylife.data

import android.util.Log
import androidx.lifecycle.LiveData

// abstracts access to multiple data sources
class ReportRepository(private val reportDao: ReportDao) {

    val readAllData: LiveData<List<Report>> = reportDao.readAllData()

    suspend fun addReport(report: Report){
        Log.d("Debug-reportRepo", report.toString())
        reportDao.addReport(report)
    }
}