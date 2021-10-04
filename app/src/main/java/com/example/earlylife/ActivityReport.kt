package com.example.earlylife

import android.graphics.Point
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import java.util.*


class ActivityReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        // calling the action bar
        val actionBar = supportActionBar

        // showing the back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        var anyChartView: AnyChartView = findViewById(R.id.any_chart_view)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)

        //Adding a progress meter
        val successMeter = AnyChart.pie()
        successMeter.innerRadius("80%")

        var data = ArrayList<DataEntry>()
        data.add(ValueDataEntry("Correct", 80))
        data.add(ValueDataEntry("Incorrect", 20))
        successMeter.data(data)


        //Adding the chart to the UI
        anyChartView.setChart(successMeter)


        //adding the report details width to the UI
        var activityDetails = findViewById<View>(R.id.activity_details)
        //activityDetails.layoutParams.width = s_width/2

        var anyChartLineView: AnyChartView = findViewById(R.id.any_chart_line_view)
        APIlib.getInstance().setActiveAnyChartView(anyChartLineView)
        //adding the line graph
        val lineGraph = AnyChart.line()

        val lineData = ArrayList<DataEntry>()
        lineData.add(ValueDataEntry("Sunday",3))
        lineData.add(ValueDataEntry("Monday",5))
        lineData.add(ValueDataEntry("Wednesday",1))
        lineData.add(ValueDataEntry("Thursday",2))
        lineData.add(ValueDataEntry("Friday",0))
        lineData.add(ValueDataEntry("Saturday",2))
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
}