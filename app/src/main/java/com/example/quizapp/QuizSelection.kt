package com.example.quizapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class QuizSelection : AppCompatActivity() {

    private lateinit var layout: LinearLayout
    private lateinit var quizzes: ArrayList<Quiz>
    private lateinit var returnButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_selection)

        quizzes = Constants.getQuizzes(this)
        
        layout = findViewById(R.id.quizzesLayout)

        returnButton = findViewById(R.id.returnButton)
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        showQuizzes()
    }

    private fun showQuizzes(){
        layout.removeAllViews()

        quizzes.forEach{quiz ->
            val button = Button(this)
            button.text = quiz.quizName
            val layoutParams = LinearLayout.LayoutParams(
                800,
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
            button.setOnClickListener{
                val intent = Intent(this@QuizSelection, QuizActivity::class.java)
                intent.putExtra("QUIZ_ID", quiz.quizId)
                startActivity(intent)
                finish()
            }
            layout.addView(button)
        }
    }
}