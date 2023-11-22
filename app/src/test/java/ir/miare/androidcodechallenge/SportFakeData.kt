package ir.miare.androidcodechallenge

import ir.miare.androidcodechallenge.domain.model.League
import ir.miare.androidcodechallenge.domain.model.Player
import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.domain.model.Team
const val count = 5
const val defaultTotalGoal = 30
const val totalGoalMultiplexRate = 2
const val defaultTotalMatches = 30
const val totalMatchesMultiplexRate = 2

fun generateSportFakeDescending(): List<Sport> {
    val sportDataList = ArrayList<Sport>()

    for(i in count downTo 1) {
        val league = League(
            id = i,
            name = "league_$i",
            country = "country_$i",
            rank = i,
            totalMatches = defaultTotalMatches + i
        )

        val playersList = ArrayList<Player>()

        for(j in count downTo 1) {
            val number = (i - 1) * count + j
            val player = Player(
                id = number,
                name = "player_$number",
                team = Team(
                    id = number,
                    name = "team_$number",
                    rank = j
                ),
                totalGoal = defaultTotalGoal + (totalGoalMultiplexRate * number)
            )

            playersList.add(player)
        }

        val sport = Sport(
            id = i,
            league = league,
            players = playersList
        )

        sportDataList.add(sport)
    }

    return sportDataList
}

fun generateSportFakeAscending(): List<Sport> {
    val sportDataList = ArrayList<Sport>()

    for(i in 1 .. count) {
        val league = League(
            id = i,
            name = "league_$i",
            country = "country_$i",
            rank = i,
            totalMatches = defaultTotalMatches + i
        )

        val playersList = ArrayList<Player>()

        for(j in 1 ..count) {
            val number = (i - 1) * count + j
            val player = Player(
                id = number,
                name = "player_$number",
                team = Team(
                    id = number,
                    name = "team_$number",
                    rank = j
                ),
                totalGoal = defaultTotalGoal + (totalGoalMultiplexRate * number)
            )

            playersList.add(player)
        }

        val sport = Sport(
            id = i,
            league = league,
            players = playersList
        )

        sportDataList.add(sport)
    }

    return sportDataList
}