package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.util.SortOrder

class SortByMostAverageGoalsPerMatchUseCase {
    /**
     *  Sort by most average goals per match in a league
     */
    operator fun invoke(
        sportDataList: List<Sport>,
        sortOrder: SortOrder = SortOrder.DESC,
    ): List<Sport> {
        return sportDataList.let { list ->
            when (sortOrder) {
                SortOrder.DESC -> {
                    list.sortedByDescending { sport ->
                        sport.players.sumOf { it.totalGoal }
                            .toDouble() / (sport.league?.totalMatches?.toDouble() ?: 1.0)
                    }
                }

                else -> {
                    list.sortedBy { sport ->
                        sport.players.sumOf { it.totalGoal }
                            .toDouble() / (sport.league?.totalMatches?.toDouble() ?: 1.0)
                    }
                }
            }
        }
    }
}