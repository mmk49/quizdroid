package edu.uw.ischool.mmk49.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TopicOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_topic_overview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val topic = getIntent().getStringExtra("TOPIC")
        val description = getIntent().getStringExtra("DESCRIPTION")
        val bundle = intent.getBundleExtra("QUESTIONS")
        val dataMap = bundle?.keySet()?.associateWith { bundle.getStringArrayList(it) }
        val answers = intent.getStringArrayListExtra("ANSWERS")
        val curr = intent.getIntExtra("CURR", 0)
        val correctCount = intent.getIntExtra("CORRECTCOUNT", 0)


        val topicText = findViewById<TextView>(R.id.TopicOverviewTopic)
        val descriptionText = findViewById<TextView>(R.id.TopicOverviewDescription)
        val totalQuestions = findViewById<TextView>(R.id.TopicOverviewTotalQuestions)
        val button = findViewById<Button>(R.id.TopicOverviewButton)

        topicText.text = topic
        descriptionText.text = description
        val questionCount = dataMap?.size.toString()
        if (dataMap != null) {
            totalQuestions.text = questionCount
        }

        button.setOnClickListener {
            val bundle = Bundle()
            dataMap?.forEach { (key, value) ->
                bundle.putStringArrayList(key, value)
            }
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("QUESTIONS", bundle)
            intent.putExtra("TOTALQUESTIONS", questionCount)
            intent.putStringArrayListExtra("ANSWERS", answers)
            intent.putExtra("CURR", curr)
            intent.putExtra("CORRECTCOUNT", correctCount)
            startActivity(intent)
        }

    }
}