package edu.uw.ischool.mmk49.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle = intent.getBundleExtra("QUESTIONS")
        val dataMap = bundle?.keySet()?.associateWith { bundle.getStringArrayList(it) }

        val count = intent.getStringExtra("TOTALQUESTIONS")
        val correctAnswers = intent.getStringArrayListExtra("ANSWERS")
        var curr = intent.getIntExtra("CURR", 0)
        var correctCount = intent.getIntExtra("CORRECTCOUNT", 0)

        val question = dataMap?.keys?.elementAtOrNull(curr)
        val answers = dataMap?.getValue(question)
        val A1 = answers?.get(0)
        val A2 = answers?.get(1)
        val A3 = answers?.get(2)
        val A4 = answers?.get(3)

        val questionText = findViewById<TextView>(R.id.Question)
        val answer1 = findViewById<RadioButton>(R.id.Answer1)
        val answer2 = findViewById<RadioButton>(R.id.Answer2)
        val answer3 = findViewById<RadioButton>(R.id.Answer3)
        val answer4 = findViewById<RadioButton>(R.id.Answer4)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val radioGroup = findViewById<RadioGroup>(R.id.answerGroup)

        questionText.text = getString(R.string.insert_text, question)
        answer1.text = getString(R.string.insert_text, A1)
        answer2.text = getString(R.string.insert_text, A2)
        answer3.text = getString(R.string.insert_text, A3)
        answer4.text = getString(R.string.insert_text, A4)
        submitButton.text = getString(R.string.insert_text, "Submit")

        var answer = -1
        var choosenAnswer = ""
        radioGroup.setOnCheckedChangeListener { _, checkedID ->
            when(checkedID) {
                answer1.id -> {
                    answer = 1
                    choosenAnswer = (answer1.text as String?).toString()
                }
                answer2.id -> {
                    answer = 2
                    choosenAnswer = (answer2.text as String?).toString()
                }
                answer3.id -> {
                    answer = 3
                    choosenAnswer = (answer3.text as String?).toString()
                }
                answer4.id -> {
                    answer = 4
                    choosenAnswer = (answer4.text as String?).toString()
                }
            }
            submitButton.isVisible = true
        }
        submitButton.setOnClickListener {
            if(answer.toString() == (correctAnswers?.get(curr) ?: "-1")) {
                correctCount++

            }
            curr++
            val bundle = Bundle()
            dataMap?.forEach { (key, value) ->
                bundle.putStringArrayList(key, value)
            }
            val intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra("QUESTIONS", bundle)
            intent.putExtra("TOTALQUESTIONS", count)
            intent.putStringArrayListExtra("ANSWERS", correctAnswers)
            intent.putExtra("CURR", curr)
            intent.putExtra("CORRECTCOUNT", correctCount)
            intent.putExtra("CHOOSENANSWER", choosenAnswer)
            startActivity(intent)
        }
    }
}