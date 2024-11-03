package edu.uw.ischool.mmk49.quizdroid.domain

import java.io.Serializable

data class Question(val questionText: String, val answers: List<String>, val correctAnswer: Int) : Serializable