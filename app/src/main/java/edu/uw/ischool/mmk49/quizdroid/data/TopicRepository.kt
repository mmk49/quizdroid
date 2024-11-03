package edu.uw.ischool.mmk49.quizdroid.data

import edu.uw.ischool.mmk49.quizdroid.domain.Topic

interface TopicRepository {
    fun getTopics(): List<Topic>
}