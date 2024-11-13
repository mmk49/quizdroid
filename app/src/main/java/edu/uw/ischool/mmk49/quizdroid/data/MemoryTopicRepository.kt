package edu.uw.ischool.mmk49.quizdroid.data

import android.content.Context
import edu.uw.ischool.mmk49.quizdroid.R
import edu.uw.ischool.mmk49.quizdroid.domain.Question
import edu.uw.ischool.mmk49.quizdroid.domain.Topic
import org.json.JSONArray

class MemoryTopicRepository(context: Context): TopicRepository {
    private val topics: List<Topic> = createTopics(context)
    private fun createTopics(context: Context) : List<Topic> {
//        return listOf(
//            Topic(
//                title = "Math",
//                shortDescription = "I love math",
//                longDescription = "I love math so much so I wrote a longer description",
//                questions = listOf(
//                    Question(
//                        questionText = "What is 1+1?",
//                        answers = listOf("4", "99", "2173", "10000"),
//                        correctAnswer = 0
//                    ),
//                    Question(
//                        questionText = "What is 2+1?",
//                        answers = listOf("3", "1", "12", "69"),
//                        correctAnswer = 0
//                    )
//                ),
//            ),
//            Topic(
//                title = "Algebra",
//                shortDescription = "I love math",
//                longDescription = "I love math so much so I wrote a longer description",
//                questions = listOf(
//                    Question(
//                        questionText = "What is 1+1?",
//                        answers = listOf("4", "99", "2173", "10000"),
//                        correctAnswer = 0
//                    ),
//                    Question(
//                        questionText = "What is 2+1?",
//                        answers = listOf("3", "1", "12", "69"),
//                        correctAnswer = 0
//                    )
//                )
//            )
//        )
        val inputStream = context.resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val topicList = mutableListOf<Topic>()
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val topicJson = jsonArray.getJSONObject(i)
            val title = topicJson.getString("title")
            val description = topicJson.getString("desc")
            val questionsJsonArray = topicJson.getJSONArray("questions")

            val questions = mutableListOf<Question>()
            for (j in 0 until questionsJsonArray.length()) {
                val questionJson = questionsJsonArray.getJSONObject(j)
                val text = questionJson.getString("text")
                val answerIndex = questionJson.getString("answer").toInt() - 1
                val answersJsonArray = questionJson.getJSONArray("answers")

                val answers = mutableListOf<String>()
                for (k in 0 until answersJsonArray.length()) {
                    answers.add(answersJsonArray.getString(k))
                }

                questions.add(
                    Question(
                        questionText = text,
                        answers = answers,
                        correctAnswer = answerIndex
                    )
                )
            }

            topicList.add(
                Topic(
                    title = title,
                    shortDescription = description,
                    longDescription = description,
                    questions = questions
                )
            )
        }
        return topicList
    }
    override fun getTopics(): List<Topic> = topics

}