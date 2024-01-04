package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val startButton = findViewById<Button>(R.id.startGameButton)
        startButton.setOnClickListener{
            val intent = Intent(this, QuizSelection::class.java)
            startActivity(intent)
        }
    }
}