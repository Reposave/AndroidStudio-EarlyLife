package com.example.earlylife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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

        val lineChartButton = findViewById<View>(R.id.line_fragment_button)
        val barChartButton = findViewById<View>(R.id.bar_fragment_button)

        lineChartButton.setOnClickListener { changeChartType(LineChartFragment()) }
        barChartButton.setOnClickListener { changeChartType(ReportBarChart()) }

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bar_chart_fragment, ReportBarChart())
        fragmentTransaction.commit()

    }

    fun startActivityReport(view: View){
        val intent = Intent(this,ActivityReport::class.java)
        startActivity(intent)
    }

    fun changeChartType(fragment: Fragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bar_chart_fragment, fragment)
        fragmentTransaction.commit()

    }
}
