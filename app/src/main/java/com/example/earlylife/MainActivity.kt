package com.example.earlylife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.google.android.material.navigation.NavigationView
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bar_chart_fragment, ReportBarChart())
        fragmentTransaction.commit()
        /*
        //val mDBManager = DBManager(this.application)
        //Creating a dummy pie chart to test
        val pie = AnyChart.pie()
        //Array with the data
        val data = ArrayList<DataEntry>()


        data.add(ValueDataEntry("Love",100))
        data.add(ValueDataEntry("Numbers",300))
        data.add(ValueDataEntry("Shapes",50))

        pie.data(data)
        //Adding the chart to the UI
        val anyChartView:AnyChartView = findViewById(R.id.any_chart_view)
        anyChartView.setChart(pie)
        */
    }

    fun startActivityReport(view: View){
        val intent = Intent(this,ActivityReport::class.java)
        startActivity(intent)
    }
}
