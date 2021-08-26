package com.example.earlylife.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReportDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //if 2 users are the same, just ignore
    suspend fun addReport(report: Report)

    @Query(value = "SELECT * FROM report_table ORDER BY id ASC")
    fun readAllData(): List<Report>

}