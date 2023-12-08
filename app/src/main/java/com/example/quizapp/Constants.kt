package com.example.quizapp

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val questionOne = Question(
            1, "Jakie jest przeciętne życie kota domowego?", listOf(
                "Około 5 lat",
                "Około 10 lat",
                "Około 15 lat",
                "Około 20 lat"
            ), 2, 30
        )

        val questionTwo = Question(
            2, "Najglupszy kot to", listOf(
                "Pchlarz",
                "Milly",
                "Jinx",
                "Teo"
            ), 3, 30
        )


        questionsList.add(questionOne)
        questionsList.add(questionTwo)

        return questionsList
    }


}