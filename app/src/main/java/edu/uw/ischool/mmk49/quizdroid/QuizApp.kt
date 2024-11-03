package edu.uw.ischool.mmk49.quizdroid

import android.util.Log

class QuizApp : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp Working", "TRUE")
    }
}