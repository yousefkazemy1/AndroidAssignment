package ir.miare.androidcodechallenge.data.mapper

import ir.miare.androidcodechallenge.data.remote.response.SportResponse
import ir.miare.androidcodechallenge.domain.model.League
import ir.miare.androidcodechallenge.domain.model.Player
import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.domain.model.Team

fun List<SportResponse>.mapToSportDataList(): List<Sport> {
    return map { sport ->
        Sport(
            league = League(
                name = sport.league.name,
                country = sport.league.country,
                rank = sport.league.rank,
                totalMatches = sport.league.totalMatches
            ),
            players = sport.players.map { player ->
                Player(
                    name = player.name,
                    team = Team(
                        name = player.team.name,
                        rank = player.team.rank
                    ),
                    totalGoal = player.totalGoal
                )
            }
        )
    }
}