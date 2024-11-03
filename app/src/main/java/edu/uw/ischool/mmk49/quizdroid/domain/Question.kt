package edu.uw.ischool.mmk49.quizdroid.domain

data class Question(val questionText: String, val answers: List<String>, val correctAnswer: Int)