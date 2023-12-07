package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val startButton = findViewById<Button>(R.id.startGameButton)
        startButton.setOnClickListener{startGame()}
    }

    private fun startGame(){
        
    }
}