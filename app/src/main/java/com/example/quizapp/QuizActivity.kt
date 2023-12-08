package com.example.quizapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginBottom

class QuizActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var questionIndexTextView: TextView
    private lateinit var layout: LinearLayout
    private lateinit var questions: List<Question>
    private var currQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.questionTextView)
        questionIndexTextView = findViewById(R.id.questionIndexTextView)
        layout = findViewById(R.id.answersLayout)

        questions = Constants.getQuestions()

        showQuestion()
    }

    private fun showQuestion() {
        layout.removeAllViews()

        val currentQuestion = questions[currQuestion]
        questionTextView.text = currentQuestion.question
        questionIndexTextView.text = "Pytanie ${currQuestion+1}"

        currentQuestion.answers.forEach { answer ->
            val button = Button(this)
            button.text = answer
            val layoutParams = LinearLayout.LayoutParams(
                650,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 20)
            button.layoutParams = layoutParams
            button.setBackgroundResource(R.drawable.border)
            button.setTextColor(resources.getColor(R.color.secondaryFontColor))
            button.setPadding(70, 15, 70, 20)
            button.textSize = 18f
            val archivio : Typeface? = ResourcesCompat.getFont(this, R.font.archivo_black)
            button.typeface = archivio
            button.setOnClickListener {
                // Check Question
            }
            layout.addView(button)
        }

    }
}