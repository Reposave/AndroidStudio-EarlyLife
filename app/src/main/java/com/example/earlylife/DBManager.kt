package com.example.earlylife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.earlylife.R
import com.example.earlylife.data.Report
import com.example.earlylife.data.ReportViewModel

//This class is in charge of managing entries in the Room Database, all Database accesses must go through this class.
class DBManager {
    private lateinit var mReportViewModel: ReportViewModel

    //Use this method to add data to the DB
    public fun insertToDbase(id: Int, activityName: String, dailyUsage:Int, weeklyUsage:Int) {
        val reportEntry = Report(id,activityName, dailyUsage, weeklyUsage)
        //Add Data to Database
        mReportViewModel.addReport(reportEntry)
        
    }

    //Returns a report object.
    private fun findReport(id:Int): Report {
        val reportIterator = mReportViewModel.readAllData.value?.iterator()

        if (reportIterator != null) {
            while (reportIterator.hasNext()) {
                val rpt = reportIterator.next()
                if (rpt.id == id) {
                    return rpt
                }
            }
        }
        return Report(0,"Fail",0,0) //PlaceHolder for now.
    }

    //Use these methods to get data from the database. Ideally, findReport should be updated to return a list.
    public fun getName(id:Int):String{
        return findReport(id).activityName
    }
    public fun getDailyUsage(id:Int): Int {
        return findReport(id).dailyUsage
    }
    public fun getWeeklyUsage(id:Int): Int {
        return findReport(id).weeklyUsage
    }


}