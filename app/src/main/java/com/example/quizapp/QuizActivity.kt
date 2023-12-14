package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import android.os.Handler
import android.widget.ProgressBar
import androidx.core.view.marginBottom

class QuizActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var questionIndexTextView: TextView
    private lateinit var layout: LinearLayout
    private lateinit var questions: List<Question>
    private lateinit var progressBar: ProgressBar
    private var currQuestion = 0
    private var isAnswerChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.questionTextView)
        questionIndexTextView = findViewById(R.id.questionIndexTextView)
        progressBar = findViewById(R.id.quizProgressBar)
        layout = findViewById(R.id.answersLayout)

        questions = Constants.getQuestions()
        progressBar.max = questions.size

        showQuestion()
    }

    private fun showQuestion() {
        layout.removeAllViews()

        val currentQuestion = questions[currQuestion]
        questionTextView.text = currentQuestion.question
        questionIndexTextView.text = "Pytanie ${currQuestion + 1}"
        progressBar.progress = currQuestion+1

        currentQuestion.answers.forEachIndexed { index, answer ->
            val button = Button(this)
            button.text = answer
            val layoutParams = LinearLayout.LayoutParams(
                750,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 50)
            button.layoutParams = layoutParams
            button.setBackgroundResource(R.drawable.border)
            button.setTextColor(resources.getColor(R.color.secondaryFontColor))
            button.setPadding(70, 50, 70, 55)
            button.textSize = 20f
            val archivio: Typeface? = ResourcesCompat.getFont(this, R.font.archivo_black)
            button.typeface = archivio
            button.setOnClickListener {
                if (!isAnswerChecked) {
                    isAnswerChecked = true
                    checkAnswer(index+1, button)
                }
            }
            layout.addView(button)
        }
    }

    private fun checkAnswer(selectedAnswer: Int, selectedButton: Button) {
        val currentQuestion = questions[currQuestion]
        val correctAnswer = currentQuestion.answer

        selectedButton.setBackgroundResource( if (selectedAnswer == correctAnswer) R.drawable.border_green else R.drawable.border_red)

        val handler = Handler()
        handler.postDelayed({
            currQuestion++
            if (currQuestion < questions.size) {
                isAnswerChecked = false
                showQuestion()
            } else {
                // koniec quizu
            }
        }, 3000)
    }
}