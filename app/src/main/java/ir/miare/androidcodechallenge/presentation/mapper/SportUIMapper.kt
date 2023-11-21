package ir.miare.androidcodechallenge.presentation.mapper

import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.presentation.model.LeagueUI
import ir.miare.androidcodechallenge.presentation.model.PlayerUI
import ir.miare.androidcodechallenge.presentation.model.RankUI

fun List<Sport>.mapToRankUIDataList(
    showTotalGoal: Boolean = false
): List<RankUI> {
    return ArrayList<RankUI>().apply {
        this@mapToRankUIDataList.forEach { sport ->
            sport.league?.let { league ->
                add(
                    RankUI(
                        league = LeagueUI(
                            name = league.name,
                            country = league.country,
                            rank = league.rank,
                            totalMatches = league.totalMatches
                        )
                    )
                )
            }

            addAll(sport.players.map { player ->
                RankUI(
                    player = PlayerUI(
                        playerName = player.name,
                        teamName = player.team.name,
                        teamRank = player.team.rank,
                        totalGoal = player.totalGoal,
                        showTotalGoal = showTotalGoal
                    )
                )
            })
        }
    }
}