package com.example.earlylife

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.earlylife.DBManager
import com.google.android.material.navigation.NavigationView
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val mDBManager = DBManager(this.application)
        //Creating a dummy pie chart to test
        val pie = AnyChart.pie()
        //Array with the data
        val data = ArrayList<DataEntry>()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
        /*
        //duplicates might be ignored.
        mDBManager.insertToDbase(1,"Love",10,100)
        mDBManager.insertToDbase(2,"Numbers",300, 400)
        mDBManager.insertToDbase(3,"Shapes",20,40)

         */

        data.add(ValueDataEntry("Love",100))
        data.add(ValueDataEntry("Numbers",300))
        data.add(ValueDataEntry("Shapes",50))

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
