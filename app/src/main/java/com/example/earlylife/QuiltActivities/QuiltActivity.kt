package com.example.earlylife.QuiltActivities

import java.io.Serializable

class QuiltActivity (var activityID:Int,
                    var activityName: String,
                    var correct: Int,
                    var timeOnTask: Float) : Serializable {

    fun addTimeOnTask(time: Float){
        this.timeOnTask = this.timeOnTask + time
    }

    fun addCorrect(correct: Int){
        this.correct = this.correct + correct
    }
}