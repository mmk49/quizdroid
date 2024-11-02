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

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_answer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle = intent.getBundleExtra("QUESTIONS")
        val dataMap = bundle?.keySet()?.associateWith { bundle.getStringArrayList(it) }

        val count = intent.getStringExtra("TOTALQUESTIONS")
        val correctAnswers = intent.getStringArrayListExtra("ANSWERS")
        val curr = intent.getIntExtra("CURR", 0)
        val correctCount = intent.getIntExtra("CORRECTCOUNT", 0)
        val choosenAnswer = intent.getStringExtra("CHOOSENANSWER")

        val correctText = findViewById<TextView>(R.id.correct)
        val choosenText = findViewById<TextView>(R.id.answer)
        val nextBtn = findViewById<Button>(R.id.next)

        correctText.text = getString(R.string.correct_total, correctCount.toString(), count)
        choosenText.text = getString(R.string.your_answer, choosenAnswer)
        nextBtn.text = getString(R.string.insert_text, "Next")

        if(count == curr.toString()) {
            nextBtn.text = getString(R.string.insert_text, "Finish")
            nextBtn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            nextBtn.setOnClickListener {
                val bundle = Bundle()
                dataMap?.forEach { (key, value) ->
                    bundle.putStringArrayList(key, value)
                }
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("QUESTIONS", bundle)
                intent.putExtra("TOTALQUESTIONS", count)
                intent.putStringArrayListExtra("ANSWERS", correctAnswers)
                intent.putExtra("CURR", curr)
                intent.putExtra("CORRECTCOUNT", correctCount)
                startActivity(intent)
            }
        }

    }
}