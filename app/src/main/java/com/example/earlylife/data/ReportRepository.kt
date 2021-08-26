package com.example.earlylife.data

import androidx.lifecycle.LiveData

// abstracts access to multiple data sources
class ReportRepository(private val reportDao: ReportDao) {

    val readAllData: LiveData<List<Report>> = reportDao.readAllData()

    suspend fun addReport(report: Report){
        reportDao.addReport(report)
    }
}