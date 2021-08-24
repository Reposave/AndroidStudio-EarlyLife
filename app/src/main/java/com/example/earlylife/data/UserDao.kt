package com.example.earlylife.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE //if 2 users are the same, just ignore
    suspend fun addUser(user: User)


}