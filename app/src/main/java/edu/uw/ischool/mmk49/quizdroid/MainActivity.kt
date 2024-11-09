package edu.uw.ischool.mmk49.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uw.ischool.mmk49.quizdroid.data.MemoryTopicRepository
import edu.uw.ischool.mmk49.quizdroid.domain.Topic

class MainActivity : AppCompatActivity(), TopicAdapter.RecyclerViewEvent {
    private lateinit var topicList: List<Topic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val topicRepository = (application as QuizApp).topicRepository
        topicList = topicRepository.getTopics()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = TopicAdapter(topicList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onItemClick(position: Int) {
        val curr = 0
        val correctCount = 0
        val item = topicList[position]
        val intent = Intent(this, TopicOverviewActivity::class.java)
        intent.putExtra("TOPICOBJ", item)
        intent.putExtra("CURR", curr)
        intent.putExtra("CORRECTCOUNT", correctCount)
        startActivity(intent)
    }
}