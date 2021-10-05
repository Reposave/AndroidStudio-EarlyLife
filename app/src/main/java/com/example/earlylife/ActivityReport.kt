package com.example.earlylife

import android.graphics.Point
import android.os.Bundle

import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull

import android.provider.BaseColumns
import android.util.Log
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.earlylife.SQLite.FeedReaderContract
import java.util.*
import kotlin.collections.ArrayList


class ActivityReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        var activityName : String? = null
        intent.extras?.getString("ActivityName")?.let { Log.d("debug", it) }
        var extras = intent.extras
        if (extras != null) {
            activityName = extras.getString("ActivityName").toString()
        }


        // calling the action bar
        val actionBar = supportActionBar

        // showing the back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        var anyChartLineView: AnyChartView = findViewById(R.id.any_chart_line_view)
        APIlib.getInstance().setActiveAnyChartView(anyChartLineView)
        //adding the line graph
        val lineGraph = AnyChart.line()

        val lineData = activityName?.let { getWeeklyActivity(it) }
        /*
        lineData.add(ValueDataEntry("Sunday",3))
        lineData.add(ValueDataEntry("Monday",5))
        lineData.add(ValueDataEntry("Wednesday",1))
        lineData.add(ValueDataEntry("Thursday",2))
        lineData.add(ValueDataEntry("Friday",0))
        lineData.add(ValueDataEntry("Saturday",2))
        *
         */
        lineGraph.data(lineData)
        anyChartLineView.setChart(lineGraph)


    }

    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun getWeeklyActivity(activityName: String): ArrayList<DataEntry>{
        val lineData = ArrayList<DataEntry>()
        val dbHelper =  FeedReaderContract.FeedReaderDbHelper(this.applicationContext)
        val dbr = dbHelper?.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK,
            FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT,
            FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)

        var selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME} = ?"
        val cursor = dbr?.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            arrayOf(activityName),          // The values for the WHERE clause
            null,                // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val date = mutableListOf<String>()
        val time = mutableListOf<Int>()
        with(cursor) {
            while (this?.moveToNext() == true) {
                val d = getString(getColumnIndexOrThrow(com.example.earlylife.SQLite.FeedReaderContract.FeedEntry.COLUMN_NAME_DATE))
                date.add(d)
                val t = getInt(getColumnIndexOrThrow(com.example.earlylife.SQLite.FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK))
                time.add(t)
            }
        }
        if (cursor != null) {
            cursor.close()
        }

        for(i in 0..date.size-1) {
            lineData.add(ValueDataEntry(date[i], time[i]))
        }
        return lineData

    }
}