package com.example.earlylife.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_table")
data class Report (
    @PrimaryKey(autoGenerate = true) //automatically generates ID numbers for reports
    val id: Int,
    val activityName: String,
    val dailyUsage: Int,
    val weeklyUsage: Int
)
    /**
     * commenting out additional fields that will be used
     * in Report Entity
     * val smartQuiltID: Int,
    val childName: String,
    val childAge: Int,
    val activityComplete: Boolean,
    val timeSpent: Int,
    val activityStatus: String*/

