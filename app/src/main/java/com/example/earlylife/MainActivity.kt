package com.example.earlylife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creating a dummy pie chart to test
        val pie = AnyChart.pie()
        val data = ArrayList<DataEntry>()
        data.add(ValueDataEntry("Love",100))
        data.add(ValueDataEntry("Numbers",200))
        data.add(ValueDataEntry("Shapes",300))
        pie.data(data)

        val anyChartView:AnyChartView = findViewById(R.id.any_chart_view)
        anyChartView.setChart(pie)
    }
}