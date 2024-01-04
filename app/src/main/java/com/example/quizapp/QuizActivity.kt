package com.example.quizapp

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import android.os.Handler
import android.widget.ProgressBar
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var questionIndexTextView: TextView
    private lateinit var layout: LinearLayout
    private lateinit var questions: List<Question>
    private lateinit var progressBar: ProgressBar
    private lateinit var answerTimeProgressbar: ProgressBar
    private var currQuestion = 0
    private var isAnswerChecked = false
    private var correctAnswers = 0
    private var wrongAnswers = 0
    private lateinit var countDownTimer: CountDownTimer
    private val timeInterval: Long = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.questionTextView)
        questionIndexTextView = findViewById(R.id.questionIndexTextView)
        progressBar = findViewById(R.id.quizProgressBar)
        answerTimeProgressbar = findViewById(R.id.answerTimeProgressBar)
        layout = findViewById(R.id.answersLayout)

        questions = Constants.getQuestions(1)!!
        progressBar.max = questions.size

        showQuestion()
    }

    private fun showQuestion() {
        layout.removeAllViews()

        val currentQuestion = questions[currQuestion]
        questionTextView.text = currentQuestion.question
        questionIndexTextView.text = "Pytanie ${currQuestion + 1}"
        progressBar.progress = currQuestion + 1
        answerTimeProgressbar.max = currentQuestion.answerTime.toInt()

        val shuffledAnswers = currentQuestion.answers.shuffled()

        var isTimeUp = false

        countDownTimer = object : CountDownTimer(currentQuestion.answerTime * 1000L, timeInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 1000).toInt()
                answerTimeProgressbar.progress = progress
            }

            override fun onFinish() {
                isTimeUp = true
                wrongAnswers++
                currQuestion++
                if (currQuestion < questions.size) {
                    showQuestion()
                } else {
                    val intent = Intent(this@QuizActivity, QuizzResult::class.java)
                    intent.putExtra("CORRECT_ANSWERS", correctAnswers)
                    intent.putExtra("WRONG_ANSWERS", wrongAnswers)
                    startActivity(intent)
                    finish()
                }
            }
        }

        countDownTimer.start()

        shuffledAnswers.forEachIndexed { _, answer ->
            val originalIndex = currentQuestion.answers.indexOf(answer)
            val button = Button(this)
            button.text = answer
            val layoutParams = LinearLayout.LayoutParams(
                750,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 50)
            button.setPadding(70, 50, 70, 55)
            button.layoutParams = layoutParams
            button.setBackgroundResource(R.drawable.border)
            button.setTextColor(ContextCompat.getColor(this, R.color.secondaryFontColor))
            button.textSize = 20f
            val archivio: Typeface? = ResourcesCompat.getFont(this, R.font.archivo_black)
            button.typeface = archivio
            button.setOnClickListener {
                if (!isAnswerChecked && !isTimeUp) {
                    isAnswerChecked = true
                    countDownTimer.cancel()
                    checkAnswer(originalIndex + 1, button)
                }
            }
            layout.addView(button)
        }
    }

    private fun checkAnswer(selectedAnswer: Int, selectedButton: Button) {
        val currentQuestion = questions[currQuestion]
        val correctAnswer = currentQuestion.answer

        if (selectedAnswer == correctAnswer) {
            selectedButton.setBackgroundResource(R.drawable.border_green)
            correctAnswers++
        } else {
            selectedButton.setBackgroundResource(R.drawable.border_red)
            wrongAnswers++
        }

        isAnswerChecked = true

        val handler = Handler()
        handler.postDelayed({
            currQuestion++
            if (currQuestion < questions.size) {
                isAnswerChecked = false
                showQuestion()
            } else {
                val intent = Intent(this, QuizzResult::class.java)
                intent.putExtra("CORRECT_ANSWERS", correctAnswers)
                intent.putExtra("WRONG_ANSWERS", wrongAnswers)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}
