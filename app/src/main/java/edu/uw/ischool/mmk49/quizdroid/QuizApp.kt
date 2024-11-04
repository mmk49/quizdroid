package edu.uw.ischool.mmk49.quizdroid

import android.util.Log
import edu.uw.ischool.mmk49.quizdroid.data.MemoryTopicRepository

class QuizApp : android.app.Application() {
    val topicRepository = MemoryTopicRepository().getTopics()
    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp Working", "TRUE")
    }
}