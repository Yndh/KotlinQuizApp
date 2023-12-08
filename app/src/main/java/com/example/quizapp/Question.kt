package com.example.quizapp

data class Question(
    val id: Int,
    val question: String,
    val answers: List<String>,
    val answer: Int,
    val answerTime: Long,
)
