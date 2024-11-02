package edu.uw.ischool.mmk49.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), TopicAdapter.RecyclerViewEvent {
    lateinit var list: MutableList<TopicModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val topicsArray = resources.getStringArray(R.array.topicsArray)
        val descriptorArray = resources.getStringArray(R.array.descriptorArray)
        val questionMap = mapOf("what is 1+1?" to arrayListOf("2", "4", "99", "10000"), "what is 2+1?" to arrayListOf("3", "422", "76", "10"))
        val answerList = arrayListOf("1", "1")

        val bundle = Bundle()
        questionMap.forEach { (key, value) ->
            bundle.putStringArrayList(key, value)
        }
        list = mutableListOf()
        for(index in topicsArray.indices) {
            list.add(TopicModel(topicsArray[index], descriptorArray[index], bundle, answerList))
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = TopicAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClick(position: Int) {
        val curr = 0
        val correctCount = 0
        val item = list[position]
        val intent = Intent(this, TopicOverviewActivity::class.java)
        intent.putExtra("TOPIC", item.topic)
        intent.putExtra("DESCRIPTION", item.description)
        intent.putExtra("QUESTIONS", item.questions)
        intent.putStringArrayListExtra("ANSWERS", item.answers)
        intent.putExtra("CURR", curr)
        intent.putExtra("CORRECTCOUNT", correctCount)
        startActivity(intent)
    }
}