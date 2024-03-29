package com.example.quizapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

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
        var quizId = intent.getIntExtra("QUIZ_ID", 0)

        questions = Constants.getQuestions(quizId, this) ?: emptyList<Question>()
        if (questions.isEmpty()) {
            val intent = Intent(this, QuizSelection::class.java)
            startActivity(intent)
            finish()
        } else {
            progressBar.max = questions.size
            showQuestion()
        }
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
                val currentQuestion = questions[currQuestion]
                val correctAnswer = currentQuestion.answer

                val handler = Handler()

                for (index in 0 until layout.childCount) {
                    val button = layout.getChildAt(index) as? Button
                    val answer = button?.text.toString()

                    if (answer == currentQuestion.answers[correctAnswer - 1]) {
                        button?.setBackgroundResource(R.drawable.border_green)
                    } else {
                        button?.setBackgroundResource(R.drawable.border_red)
                        if (answer != "") {
                            wrongAnswers++
                        }
                    }
                }

                handler.postDelayed({
                    currQuestion++
                    if (currQuestion < questions.size) {
                        isAnswerChecked = false
                        showQuestion()
                    } else {
                        val intent = Intent(this@QuizActivity, QuizzResult::class.java)
                        intent.putExtra("CORRECT_ANSWERS", correctAnswers)
                        intent.putExtra("WRONG_ANSWERS", wrongAnswers)
                        startActivity(intent)
                        finish()
                    }
                }, 2000)
            }

        }

        countDownTimer.start()

        shuffledAnswers.forEachIndexed { _, answer ->
            val originalIndex = currentQuestion.answers.indexOf(answer)
            val button = Button(this)
            button.text = answer
            val layoutParams = LinearLayout.LayoutParams(
                850,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 50)
            button.setPadding(50, 50, 50, 55)
            button.layoutParams = layoutParams
            button.setBackgroundResource(R.drawable.border)
            button.setTextColor(ContextCompat.getColor(this, R.color.secondaryFontColor))
            val textLength = answer.length
            val textSize = when {
                textLength > 15 -> 14f
                textLength > 10 -> 18f
                else -> 20f
            }
            button.textSize = textSize

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

            val correctButtonIndex = currentQuestion.answers.indexOfFirst { it == currentQuestion.answers[correctAnswer - 1] }
            val correctButton = layout.getChildAt(correctButtonIndex) as? Button
            correctButton?.setBackgroundResource(R.drawable.border_green)
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
        }, 2000)
    }
}
