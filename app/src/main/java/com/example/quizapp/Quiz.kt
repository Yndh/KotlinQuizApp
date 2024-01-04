package com.example.quizapp

data class Quiz(
    val quizId: Int,
    val quizName: String,
    val questions: ArrayList<Question>,
)