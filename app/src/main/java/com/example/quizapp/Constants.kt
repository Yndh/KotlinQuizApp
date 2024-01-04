package com.example.quizapp

object Constants {
    fun getQuizzes(): ArrayList<Quiz>{
        val quizzesList = ArrayList<Quiz>()

        val kapitanBombaQuiz = arrayListOf(
            Question(
                1, "Wybierz poprawny opis Kurvinoxa", listOf(
                    "Silny ale wolny",
                    "Sprytny ale głupi",
                    "Mądry ale słaby",
                    "Mądry i silny"
                ), 2, 30
            ),
            Question(
                2, "Jak Mkbewe jest nazywany przez kosmitów", listOf(
                    "Szatan",
                    "Zabójca",
                    "Diabeł",
                    "Behemot"
                ), 3, 30
            ),
            Question(
                3, "Kto ma władzę w galaktyce Kujwdubie?", listOf(
                    "Papież",
                    "Sułtan Kosmitów",
                    "Senator Chujewów",
                    "Gwiezdna Flota"
                ), 1, 30
            ),
            Question(
                4, "Ile procent alkoholu ma wódka \"Skurwolańska\"", listOf(
                    "9%",
                    "7%",
                    "37.5%",
                    "40%"
                ), 2, 30
            ),
            Question(
                5, "Jak ma na imię Kapitan Bomba", listOf(
                    "Sebastian",
                    "Tytus",
                    "Janek",
                    "Grzesiek"
                ), 2, 30
            ),
            Question(
                6, "Co obiecał za darmo dla wszystkich Domino Jachaś", listOf(
                    "Hot Dogi",
                    "Burgery",
                    "Pizzę Hawajską",
                    "Kebaba z Chrzanem"
                ), 3, 30
            )
        )

        val quiz2t = arrayListOf(
            Question(
                1, "Pytanie 1", listOf(
                    "Odpowiedź A",
                    "Odpowiedź B",
                    "Odpowiedź C",
                    "Odpowiedź D"
                ), 3, 30
            ),
            Question(
                2, "Pytanie 2", listOf(
                    "Odpowiedź E",
                    "Odpowiedź F",
                    "Odpowiedź G",
                    "Odpowiedź H"
                ), 1, 30
            ),
        )

        val quiz1 = Quiz(1, "Kapitan Bomba", kapitanBombaQuiz)
        val quiz2 = Quiz(2, "Quiz 2", quiz2t)

        quizzesList.add(quiz1)
        quizzesList.add(quiz2)

        return quizzesList
    }

    fun getQuestions(quizId: Int): ArrayList<Question>? {
        val quiz = getQuizzes().find{ it.quizId == quizId}
        return quiz?.questions
    }
}