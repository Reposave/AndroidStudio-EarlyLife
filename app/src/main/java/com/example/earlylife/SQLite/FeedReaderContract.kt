package com.example.earlylife.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "Activities"
        const val COLUMN_NAME_ACTIVITY_NAME = "actiVityName"
        const val COLUMN_NAME_ACTIVITY_ID = "activityID"
        const val COLUMN_NAME_DATE = "date"
        const val COLUMN_NAME_TIME_ON_TASK = "timeOnTask"
        const val COLUMN_NAME_CORRECT = "correct"
    }

    private const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME_ACTIVITY_NAME} TEXT," +
                "${FeedEntry.COLUMN_NAME_ACTIVITY_ID} TEXT," +
                "${FeedEntry.COLUMN_NAME_DATE} TEXT," +
                "${FeedEntry.COLUMN_NAME_CORRECT} TEXT," +
                "${FeedEntry.COLUMN_NAME_TIME_ON_TASK} TEXT)"

    private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"

    class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }
        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(db, oldVersion, newVersion)
        }
        companion object {
            // If you change the database schema, you must increment the database version.
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "FeedReader.db"
        }
    }
}
