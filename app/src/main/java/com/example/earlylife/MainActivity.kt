/**
 * @author Tinashe Mukundi Chitamba
 * This is the main class activity for the report overview page of the Early life
 * application
 */
package com.example.earlylife

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.earlylife.Models.LearnShapes
import com.example.earlylife.Models.Quilt
import com.example.earlylife.QuiltActivities.QuiltActivity
import com.google.android.material.navigation.NavigationView
import java.util.*
import com.example.earlylife.Retrofit.RetrofitService
import com.example.earlylife.SQLite.FeedReaderContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import androidx.appcompat.app.ActionBar;


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = FeedReaderContract.FeedReaderDbHelper(this.applicationContext)

        // calling the action bar
        val actionBar = supportActionBar

        // showing the back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        //instantiating and setting values for the spinner
        val spinner: Spinner = findViewById(R.id.date_range_spinner)
        var txt_activityID = findViewById<TextView>(R.id.sensor_data)
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
         * We want to first connect to the databse, then get data from the database
         */
        val dbr = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK,
            FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT,
            FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)

        var activityData:ArrayList<QuiltActivity> = arrayListOf(
            QuiltActivity(0,"Love",34,getTimeOnTask("loves").toFloat()),
            QuiltActivity(1,"Numbers",12,getTimeOnTask("numbers").toFloat()),
            QuiltActivity(2,"Shapes",13,getTimeOnTask("shapes").toFloat()),
            QuiltActivity(2,"Match",13,getTimeOnTask("match").toFloat())
        )
        val cardTitleText = findViewById<TextView>(R.id.card_title)

        //db access test
        val spinnerListener = DateRangeSpinnerActivity(this)
        spinner.onItemSelectedListener = spinnerListener
        //Creating variables for the buttons on the screen and setting on click listeners for the buttons
        val lineChartButton = findViewById<View>(R.id.line_fragment_button) //all
        val barChartButton = findViewById<View>(R.id.bar_fragment_button).setOnClickListener {
            setDisplayActivity(2, activityData,cardTitleText, "shapes")
        } //shapes
        val loveChartButton = findViewById<View>(R.id.love_fragment_btn).setOnClickListener{
            setDisplayActivity(0,activityData,cardTitleText,"loves")
        } //love
        val numbersChartButton = findViewById<View>(R.id.numbers_fragment_btn).setOnClickListener {
            setDisplayActivity(1, activityData,cardTitleText, "numbers")
        } //numbers
        val matchChartButton = findViewById<View>(R.id.match_shapes_fragment_btn).setOnClickListener {
            setDisplayActivity(1, activityData,cardTitleText, "march")
        } //numbers
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
    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
        var txt_activityID = findViewById<TextView>(R.id.sensor_data)
        txt_activityID.text = t.toString()
        Log.d("ERROR",t.toString())

    }

    private fun onResponse(response: Quilt) {
        Log.d("Response",response.toString())
        var txt_activityID = findViewById<TextView>(R.id.sensor_data)
        txt_activityID.text = response.LearnShapes.activityID

        //saving to database
        val dbHelper = FeedReaderContract.FeedReaderDbHelper(this.applicationContext)
        // Gets the data repository in write mode
        val db = dbHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        val learnShapesValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.LearnShapes.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.LearnShapes.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.LearnShapes.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.LearnShapes.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.LearnShapes.correct)
        }

        val learnNumbersValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.LearnShapes.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.LearnShapes.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.LearnShapes.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.LearnShapes.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.LearnShapes.correct)
        }

        val loveValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.Love.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.Love.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.Love.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.Love.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.Love.correct)
        }
        val matchShapesValues = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID, response.MarchShapes.activityID)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME, response.MarchShapes.acticityName)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, response.MarchShapes.date)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK, response.MarchShapes.timeOnTask)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, response.MarchShapes.correct)
        }

        Log.d("Debug","Values created")
        // Insert the new row, returning the primary key value of the new row
        var newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, learnShapesValues)
        newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, loveValues)
        newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, matchShapesValues)
        newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, learnNumbersValues)

        //reading the values from database
        val dbr = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK,
            FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT,
            FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)

        var selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME} = ?"
        val cursor = dbr.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            arrayOf("shapes"),          // The values for the WHERE clause
            null,                // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val itemIds = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT))
                itemIds.add(itemId)
            }
        }
        cursor.close()
        Log.e("Database", itemIds.toString())
        txt_activityID.setText(itemIds.toString())
    }

    /**
     * Function takes in an activity name and returns the time on task for the activity
     */
    fun getTimeOnTask(activityName: String): Int{
        var timeOnTask = 0
        val dbHelper = FeedReaderContract.FeedReaderDbHelper(this.applicationContext)
        val dbr = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK,
            FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT,
            FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)

        var selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_ACTIVITY_NAME} = ?"
        val cursor = dbr.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            arrayOf(activityName),          // The values for the WHERE clause
            null,                // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val shapesStats = mutableListOf<Int>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getInt(getColumnIndexOrThrow(com.example.earlylife.SQLite.FeedReaderContract.FeedEntry.COLUMN_NAME_TIME_ON_TASK))
                shapesStats.add(itemId)
            }
        }
        cursor.close()
        Log.d("Debug", shapesStats.toString())
        for(a in shapesStats){
            if (a != null) {
                timeOnTask += a //Integer.getInteger(a)
            }
        }
        return timeOnTask
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
    fun setDisplayActivity(activityID:Int,activityData: ArrayList<QuiltActivity>,cardTitle: TextView, activityName: String ){
        cardTitle.setText(activityData.get(activityID).activityName)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        var activityFragment = LineChartFragment()
        var bundle = Bundle()
        bundle.putSerializable("QuiltData",activityData)
        bundle.putInt("ActivityID",activityID)
        bundle.putString("ActivityName",activityName)
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
