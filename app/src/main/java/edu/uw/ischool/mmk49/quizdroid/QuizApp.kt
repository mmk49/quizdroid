package edu.uw.ischool.mmk49.quizdroid

import android.util.Log
import edu.uw.ischool.mmk49.quizdroid.data.MemoryTopicRepository

class QuizApp : android.app.Application() {
    lateinit var topicRepository: MemoryTopicRepository
    override fun onCreate() {
        super.onCreate()
        topicRepository = MemoryTopicRepository(this)
        Log.i("QuizApp Working", "TRUE")
    }

}