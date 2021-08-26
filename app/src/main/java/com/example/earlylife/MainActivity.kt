package com.example.earlylife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.earlylife.DBManager
import java.util.*


class MainActivity : AppCompatActivity() {
    val mDBManager = DBManager(getApplication())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Creating a dummy pie chart to test
        val pie = AnyChart.pie()
        //Array with the data
        val data = ArrayList<DataEntry>()

        //duplicates might be ignored.
        mDBManager.insertToDbase(1,"Love",10,100)
        mDBManager.insertToDbase(2,"Numbers",300, 400)
        mDBManager.insertToDbase(3,"Shapes",20,40)

        data.add(ValueDataEntry(mDBManager.getName(1),mDBManager.getDailyUsage(1)))
        data.add(ValueDataEntry(mDBManager.getName(2),mDBManager.getDailyUsage(2)))
        data.add(ValueDataEntry(mDBManager.getName(3),mDBManager.getDailyUsage(3)))

        pie.data(data)
        //Adding the chart to the UI
        val anyChartView:AnyChartView = findViewById(R.id.any_chart_view)
        anyChartView.setChart(pie)
    }

    fun startActivityReport(view: View){
        val intent = Intent(this,ActivityReport::class.java)
        startActivity(intent)
    }
}
