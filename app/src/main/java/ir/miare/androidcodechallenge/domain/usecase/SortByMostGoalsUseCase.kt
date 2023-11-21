package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.model.League
import ir.miare.androidcodechallenge.domain.model.Player
import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.util.SortOrder

class SortByMostGoalsUseCase {
    /**
     *  Sort by most goals scored by a player
     */
    operator fun invoke(
        sportDataList: List<Sport>,
        sortOrder: SortOrder = SortOrder.DESC,
    ): List<Sport> {
        val playersList = ArrayList<Player>()
        sportDataList.forEach { sport ->
            sport.players.forEach { player ->
                playersList.add(
                    player.copy(
                        id = player.id,
                        name = player.name,
                        team = player.team,
                        totalGoal = player.totalGoal
                    )
                )
            }
        }
        val sortedPlayersList = when (sortOrder) {
            SortOrder.DESC -> {
                playersList.sortedByDescending { it.totalGoal }
            }

            else -> {
                playersList.sortedBy { it.totalGoal }
            }
        }

        return listOf(
            Sport(players = sortedPlayersList)
        )
    }
}