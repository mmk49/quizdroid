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
import edu.uw.ischool.mmk49.quizdroid.domain.Question
import edu.uw.ischool.mmk49.quizdroid.domain.Topic

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

        val curr = intent.getIntExtra("CURR", 0)
        val correctCount = intent.getIntExtra("CORRECTCOUNT", 0)
        val item = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("TOPICOBJ", Topic::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("TOPICOBJ") as? Topic
        }

        val topicText = findViewById<TextView>(R.id.TopicOverviewTopic)
        val descriptionText = findViewById<TextView>(R.id.TopicOverviewDescription)
        val totalQuestions = findViewById<TextView>(R.id.TopicOverviewTotalQuestions)
        val button = findViewById<Button>(R.id.TopicOverviewButton)

        topicText.text = getString(R.string.insert_text, item?.title)
        descriptionText.text = getString(R.string.insert_text, item?.longDescription)
        totalQuestions.text = getString(R.string.total_question, item?.questions?.size.toString())
        button.text = getString(R.string.insert_text, "Begin")

        button.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("TOPICOBJ", item)
            intent.putExtra("CURR", curr)
            intent.putExtra("CORRECTCOUNT", correctCount)
            startActivity(intent)
        }

    }
}