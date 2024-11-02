package edu.uw.ischool.mmk49.quizdroid

import android.os.Bundle

data class TopicModel(val topic: String, val description: String, val questions: Bundle, val answers: ArrayList<String>) {}