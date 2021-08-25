package com.example.earlylife.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false) // set to true if we want to export the schema to a folder
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    // everything will be visible to other classes
   /** companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        private val LOCK = Any()

        // Checks if instance of database exists and returns it. Otherwise, creates a new instance and returns that
        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // if doesn't exist, create a new instance
            val instance = Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }*/
    companion object {
        @Volatile private var instance: UserDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: getDatabase(context).also { instance = it}
        }

        private fun getDatabase(context: Context) = Room.databaseBuilder(context,
            UserDatabase::class.java, "todo-list.db")
            .build()
    }
}
