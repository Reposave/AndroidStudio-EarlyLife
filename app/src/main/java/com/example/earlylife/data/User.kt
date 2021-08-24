package com.example.earlylife.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true) //automatically generates ID numbers for users
    val id: Int,
    val firstName: String,
    val lastName: String,
    val smartQuiltID: Int,
    val childName: String,
    val childAge: Int,
    val activityName: String,
    val activityComplete: Boolean,
    val timeSpent: Int,
    val activityStatus: String
)