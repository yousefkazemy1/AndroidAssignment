package ir.miare.androidcodechallenge

import ir.miare.androidcodechallenge.domain.model.League
import ir.miare.androidcodechallenge.domain.model.Player
import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.domain.model.Team

fun getSportFakeDate(): List<Sport> {
    val sportData1 = Sport(
        id = 1,
        league = League(
            id = 1,
            name = "Serie A",
            country = "Italy",
            rank = 3,
            totalMatches = 32
        ),
        players = listOf(
            Player(
                id = 1,
                name = "Edin Dzeko",
                totalGoal = 17,
                team = Team(
                    id = 1,
                    name = "Inter",
                    rank = 2
                )
            ),
            Player(
                id = 2,
                name = "Angel Di Maria",
                totalGoal = 9,
                team = Team(
                    id = 2,
                    name = "Juventus",
                    rank = 3
                )
            ),
            Player(
                id = 3,
                name = "Zlatan Ibrahimovic",
                totalGoal = 17,
                team = Team(
                    id = 3,
                    name = "Ac Milan",
                    rank = 1
                )
            )
        )
    )

    val sportData2 = Sport(
        id = 2,
        league = League(
            id = 2,
            name = "Premier League",
            country = "England",
            rank = 1,
            totalMatches = 38
        ),
        players = listOf(
            Player(
                id = 4,
                name = "Mohammad Salah",
                totalGoal = 25,
                team = Team(
                    id = 4,
                    name = "Liverpool",
                    rank = 2
                )
            ),
            Player(
                id = 5,
                name = "Erling Haaland",
                totalGoal = 33,
                team = Team(
                    id = 5,
                    name = "Man City",
                    rank = 1
                )
            ),
            Player(
                id = 6,
                name = "Marcus Rashford",
                totalGoal = 17,
                team = Team(
                    id = 6,
                    name = "Man United",
                    rank = 3
                )
            )
        )
    )

    val sportData3 = Sport(
        id = 3,
        league = League(
            id = 3,
            name = "LaLiga",
            country = "Spain",
            rank = 2,
            totalMatches = 36
        ),
        players = listOf(
            Player(
                id = 7,
                name = "Antoine Griezmann",
                totalGoal = 21,
                team = Team(
                    id = 7,
                    name = "Atletico",
                    rank = 3
                )
            ),
            Player(
                id = 8,
                name = "Karim Benzema",
                totalGoal = 27,
                team = Team(
                    id = 8,
                    name = "Real Madrid",
                    rank = 2
                )
            ),
            Player(
                id = 9,
                name = "Robert Lewandowski",
                totalGoal = 23,
                team = Team(
                    id = 9,
                    name = "Barcelona",
                    rank = 1
                )
            )
        )
    )

    return listOf(
        sportData1,
        sportData2,
        sportData3
    )
}