package com.example.earlylife

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.earlylife.QuiltActivities.QuiltActivity
import com.example.earlylife.SQLite.FeedReaderContract
import java.util.ArrayList
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LineChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LineChartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var activityName: String? = null
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_line_chart, container, false)
       // return activity?.let { LineChart(it) }
    }

    override fun onStart() {
        super.onStart()

        var bundle = this.arguments
        var Activitydata:ArrayList<QuiltActivity> = bundle?.get("QuiltData") as ArrayList<QuiltActivity>
        var id = bundle?.getInt("ActivityID")
        activityName = bundle?.getString("ActivityName")

        var activity = Activitydata.get(id)

        //var anyChartView = (getView()?.findViewById(R.id.any_chart_view)) as AnyChartView
        //APIlib.getInstance().setActiveAnyChartView(anyChartView)
        //var txtView = getView()?.findViewById<TextView>(R.id.info_text)
        var btnStartActivity = getView()?.findViewById<Button>(R.id.btn_individual_activity)
        if (btnStartActivity != null) {
            btnStartActivity.setOnClickListener{ startIndividualActivityReport() }
        }

        //Adding a progress meter
        /*
        val successMeter = AnyChart.pie()
        successMeter.innerRadius("60%")
        val correct = activityName?.let { getCorrect(it, txtView) }
        val data = ArrayList<DataEntry>()
        data.add(ValueDataEntry("Correct",correct))
        data.add(ValueDataEntry("Incorrect", 500 - correct!!))
        successMeter.data(data)
        *
         */

        //Adding the chart to the UI
        //anyChartView.setChart(successMeter)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LineChartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LineChartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun startIndividualActivityReport(){
        val intent = Intent(activity,ActivityReport::class.java)
        intent.putExtra("ActivityName",activityName)
        startActivity(intent)
    }

    fun getCorrect(activityName: String, txtView: TextView?): Int{
        var correct = 0
        val dbHelper = context?.let { FeedReaderContract.FeedReaderDbHelper(it) }
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

        val shapesStats = mutableListOf<Int>()
        with(cursor) {
            while (this?.moveToNext() == true) {
                val itemId = getInt(getColumnIndexOrThrow(com.example.earlylife.SQLite.FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT))
                shapesStats.add(itemId)
            }
        }
        if (cursor != null) {
            cursor.close()
        }
        Log.d("Debug", shapesStats.toString())
        for(a in shapesStats){
            if (a != null) {
                correct += a //Integer.getInteger(a)
            }
        }
        if (txtView != null) {
            txtView.setText(correct.toString())
        }
        return correct
    }
}