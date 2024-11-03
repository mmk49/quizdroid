package edu.uw.ischool.mmk49.quizdroid.data

import edu.uw.ischool.mmk49.quizdroid.domain.Question
import edu.uw.ischool.mmk49.quizdroid.domain.Topic

class MemoryTopicRepository: TopicRepository {
    private val topics: List<Topic> = listOf(
        Topic(
            title = "Math",
            shortDescription = "I love math",
            longDescription = "I love math so much so I wrote a longer description",
            questions = listOf(
                Question(
                    questionText = "What is 1+1?",
                    answers = listOf("4", "99", "2173", "10000"),
                    correctAnswer = 0
                ),
                Question(
                    questionText = "What is 2+1?",
                    answers = listOf("3", "1", "12", "69"),
                    correctAnswer = 0
                )
            )
        )
    )
    override fun getTopics(): List<Topic> = topics

}