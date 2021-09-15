package com.example.earlylife.QuiltActivities

class QuiltActivity(var activityID:Int,
                    var activityName: String,
                    var correct: Int,
                    var timeOnTask: Float) {

    fun addTimeOnTask(time: Float){
        this.timeOnTask = this.timeOnTask + time
    }

    fun addCorrect(correct: Int){
        this.correct = this.correct + correct
    }
}