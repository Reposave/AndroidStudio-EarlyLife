/**
 * @author Tinashe Mukundi Chitamba
 * This is the main class activity for the report overview page of the Early life
 * application
 */
package com.example.earlylife

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.earlylife.Models.Quilt
import com.example.earlylife.QuiltActivities.QuiltActivity
import com.google.android.material.navigation.NavigationView
import java.util.*
import com.tillster.smartquiltkotlin.Retrofit.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instantiating and setting values for the spinner
        val spinner: Spinner = findViewById(R.id.date_range_spinner)
        //adding the options from the resource xml file
        ArrayAdapter.createFromResource(
            this,
            R.array.date_range_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        /**
         * Given that we have retrieved data from the db and is in an arraylist
         */
        var activityData:ArrayList<QuiltActivity> = arrayListOf(
            QuiltActivity(0,"Love",34,14.toFloat()),
            QuiltActivity(1,"Numbers",12,34.toFloat()),
            QuiltActivity(2,"Shapes",13,56.toFloat())
        )
        val cardTitleText = findViewById<TextView>(R.id.card_title)

        val spinnerListener = DateRangeSpinnerActivity(this)
        spinner.onItemSelectedListener = spinnerListener
        //Creating variables for the buttons on the screen and setting on click listeners for the buttons
        val lineChartButton = findViewById<View>(R.id.line_fragment_button) //all
        val barChartButton = findViewById<View>(R.id.bar_fragment_button).setOnClickListener { setDisplayActivity(2, activityData,cardTitleText) } //shapes
        val loveChartButton = findViewById<View>(R.id.love_fragment_btn).setOnClickListener{ setDisplayActivity(0,activityData,cardTitleText) } //love
        val numbersChartButton = findViewById<View>(R.id.numbers_fragment_btn).setOnClickListener { setDisplayActivity(1, activityData,cardTitleText) } //numbers

        lineChartButton.setOnClickListener { setDefaultChart(activityData) } //all
        //lineChartButton.setOnClickListener { changeChartType(LineChartFragment()) }
        setDefaultChart(activityData)



        /*Using retrofit to get sensor data */
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitService.ServiceBuilder.buildService().getShapes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))

    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
        var txt_activityID = findViewById<TextView>(R.id.sensor_data)

        txt_activityID.text = t.message

    }

    private fun onResponse(response: Quilt) {
        var txt_activityID = findViewById<TextView>(R.id.sensor_data)
        txt_activityID.text = response.name
    }

    /**
     * Function starts the individual report activity, invoked when button clicked
     */
    fun startActivityReport(view: View){
        val intent = Intent(this,ActivityReport::class.java)
        startActivity(intent)
    }

    /**
     * Function changes the type of chart displayed on the current screen
     */
    fun changeChartType(fragment: Fragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bar_chart_fragment, fragment)
        fragmentTransaction.commit()
    }
    fun setDisplayActivity(activityID:Int,activityData: ArrayList<QuiltActivity>,cardTitle: TextView ){
        cardTitle.setText(activityData.get(activityID).activityName)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        var activityFragment = LineChartFragment()
        var bundle = Bundle()
        bundle.putSerializable("QuiltData",activityData)
        bundle.putInt("ActivityID",activityID)
        activityFragment.arguments = bundle
        fragmentTransaction.replace(R.id.bar_chart_fragment, activityFragment)
        fragmentTransaction.commit()
    }


    /**
     * sets the default bar chart
     */
    fun setDefaultChart(activityData: ArrayList<QuiltActivity>){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        var barFragment = ReportBarChart()
        var bundle = Bundle()
        bundle.putSerializable("QuiltData",activityData)
        barFragment.arguments = bundle
        fragmentTransaction.replace(R.id.bar_chart_fragment, barFragment)
        fragmentTransaction.commit()
    }

}

/**
 * Spinner class for handling on click functions for the spinner
 */
class DateRangeSpinnerActivity(context: Context) : Activity(), AdapterView.OnItemSelectedListener {
    var context: Context? = null

    init{
        this.context = context
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Toast.makeText(this.context, parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}
