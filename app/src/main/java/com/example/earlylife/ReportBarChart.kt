package com.example.earlylife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import com.example.earlylife.QuiltActivities.QuiltActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReportBarChart.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportBarChart : Fragment() {
    // TODO: Rename and change types of parameters
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
        //return inflater.inflate(R.layout.fragment_report_bar_chart, container, false)
        /**here we will create a connection tp the database, get all the rows and create an object of
        type quiltactivity, add the objects to an araay, the use the array as a paramter
         for the bar graph*/
        var activityData:ArrayList<QuiltActivity> = arrayListOf(
            QuiltActivity(0,"Love",34,14.toFloat()),
            QuiltActivity(1,"Numbers",12,34.toFloat()),
            QuiltActivity(2,"Pattern",13,56.toFloat())
        )
        return activity?.let { BarChart(it,activityData) }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReportBarChart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReportBarChart().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}