package com.example.quizapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

object Constants {

    private var quizzes: ArrayList<Quiz>? = null

    fun loadQuizzes(context: Context): ArrayList<Quiz>{
        if (quizzes == null) {
            quizzes = ArrayList()
            try {
                val inputStream: InputStream = context.resources.openRawResource(R.raw.quizzes)
                val reader = inputStream.bufferedReader()

                val listType = object : TypeToken<ArrayList<Quiz>>() {}.type
                val gson = Gson()
                quizzes?.addAll(gson.fromJson(reader, listType))

                inputStream.close()
            } catch (e: Exception) {
                println("===========")
                e.printStackTrace()
            }
        }
        return quizzes ?: ArrayList()

    }
    fun getQuizzes(context: Context): ArrayList<Quiz>{
        return loadQuizzes(context)
    }

    fun getQuestions(quizId: Int, context: Context): ArrayList<Question>? {
        val quiz = getQuizzes(context).find{ it.quizId == quizId}
        return quiz?.questions
    }
}