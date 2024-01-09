package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizzResult : AppCompatActivity() {

    private lateinit var correctAnswersTextView: TextView
    private lateinit var wrongAnswersTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var percentTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_result)

        var correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val incorrectAnswers = intent.getIntExtra("WRONG_ANSWERS", 0)
        val totalQuestions = correctAnswers+incorrectAnswers

        correctAnswersTextView = findViewById(R.id.correctAnswers)
        wrongAnswersTextView = findViewById(R.id.wrongAnswers)
        progressBar = findViewById(R.id.quizResultProgressBar)
        percentTextView = findViewById(R.id.percentTextView)


        correctAnswersTextView.text = "$correctAnswers / ${totalQuestions}"
        wrongAnswersTextView.text = "$incorrectAnswers / ${totalQuestions}"
        progressBar.max = totalQuestions
        progressBar.progress = correctAnswers
        val percent = ((correctAnswers.toFloat() / totalQuestions) * 100).toInt()
        percentTextView.text = "$percent%"



        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener{
            val intent = Intent(this, QuizSelection::class.java)
            startActivity(intent)
            finish() // zakaz cofania
        }
    }
}