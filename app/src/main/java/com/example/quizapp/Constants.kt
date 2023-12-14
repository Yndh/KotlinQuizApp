package com.example.quizapp

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val questionsData = listOf(
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

        for (data in questionsData) {
            questionsList.add(data)
        }

        return questionsList
    }
}
