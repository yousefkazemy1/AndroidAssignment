package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.mapper.convertToPlayersList
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
        return sportDataList.convertToPlayersList().let { playersList ->
            when (sortOrder) {
                SortOrder.DESC -> {
                    playersList.sortedByDescending { it.totalGoal }
                }

                else -> {
                    playersList.sortedBy { it.totalGoal }
                }
            }
        }.let { sortedPlayersList ->
            listOf(
                Sport(players = sortedPlayersList)
            )
        }
    }
}