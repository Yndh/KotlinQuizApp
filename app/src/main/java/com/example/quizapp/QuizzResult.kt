package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class QuizzResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_result)

        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val incorrectAnswers = intent.getIntExtra("WRONG_ANSWERS", 0)
        val totalQuestions = correctAnswers+incorrectAnswers

        val correctAnswersTextView = findViewById<TextView>(R.id.correctAnswers)
        val wrongAnswersTextView = findViewById<TextView>(R.id.wrongAnswers)
        val progressBar = findViewById<ProgressBar>(R.id.quizResultProgressBar)
        val percentTextView = findViewById<TextView>(R.id.percentTextView)


        correctAnswersTextView.text = "$correctAnswers / ${correctAnswers + incorrectAnswers}"
        wrongAnswersTextView.text = "$incorrectAnswers / ${correctAnswers + incorrectAnswers}"
        progressBar.max = totalQuestions
        progressBar.progress = correctAnswers
        val percent = ((correctAnswers.toFloat() / totalQuestions) * 100).toInt()
        percentTextView.text = "$percent%"



        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // zakaz cofania
        }
    }
}