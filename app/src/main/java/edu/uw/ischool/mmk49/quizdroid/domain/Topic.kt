package edu.uw.ischool.mmk49.quizdroid.domain

data class Topic(val title: String, val shortDescription: String, val longDescription: String, val questions: List<Question>)