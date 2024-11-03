package edu.uw.ischool.mmk49.quizdroid.domain

import java.io.Serializable

data class Topic(val title: String, val shortDescription: String, val longDescription: String, val questions: List<Question>)  : Serializable