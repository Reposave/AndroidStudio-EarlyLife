package com.example.earlylife

import android.content.Context
import com.example.earlylife.QuiltActivities.QuiltActivity
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.area
import io.data2viz.charts.chart.mark.bar
import io.data2viz.charts.chart.mark.line
import io.data2viz.charts.chart.quantitative
import io.data2viz.geom.Size
import io.data2viz.viz.VizContainerView

class BarChart (context: Context,val quiltActivities: ArrayList<QuiltActivity>) : VizContainerView(context){
    private var barChartData:ArrayList<PopCount> = addData()
    private val chart: Chart<PopCount> = chart(barChartData) {
        size = Size(vizSize, vizSize)
        //title = "Usage of quilt activities"

        // Create a discrete dimension for the actvities
        val activity = discrete({ domain.activity })

        // Create a continuous numeric dimension for the population
        val population = quantitative({ domain.population.toDouble() }) {
            name = "Overall quilt usage by activity (in hours)"
        }

        // Using a discrete dimension for the X-axis and a continuous one for the Y-axis
        bar(activity, population)
    }

    fun addData():ArrayList<PopCount>{
        var data:ArrayList<PopCount> = ArrayList()
        for (activity in this.quiltActivities){
            data.add(PopCount(activity.activityName,activity.timeOnTask))
        }
        return data
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * h / w)
    }
}

class LineChart (context: Context) : VizContainerView(context){
    private val chart: Chart<PopCount> = chart(canPop) {
        //adding the data to the graph

        size = Size(vizSize, vizSize)
        //title = "Usage of quilt activities"

        // Create a discrete dimension for the year of the census
        val activity = discrete({ domain.activity })

        // Create a continuous numeric dimension for the population
        val population = quantitative({ domain.population.toDouble() }) {
            name = "Overall quilt usage by activity (in hours)"
        }

        // Using a discrete dimension for the X-axis and a continuous one for the Y-axis
        line(activity, population)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * h / w)
    }
}
const val vizSize = 500.0

data class PopCount(val activity: String, val population: Float)

var canPop = listOf(
    PopCount("Love", 13.7.toFloat()),
    PopCount("Shapes", 26.429.toFloat()),
    PopCount("Numbers", 50.007.toFloat())
)

