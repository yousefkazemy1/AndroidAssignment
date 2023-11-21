package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.util.SortOrder

class SortByLeagueAndTeamRankingUseCase {
    /**
     * Sort by leagues' rank
     * Sort by players' team rank
     */
    operator fun invoke(
        sportDataList: List<Sport>,
        sortOrder: SortOrder = SortOrder.DESC,
    ): List<Sport> {
        return sportDataList.let { list ->
            when (sortOrder) {
                SortOrder.DESC -> {
                    list.sortedBy { it.league?.rank }
                }

                else -> {
                    list.sortedByDescending { it.league?.rank }
                }
            }
        }.map { sport ->
            when (sortOrder) {
                SortOrder.DESC -> {
                    sport.copy(
                        players = sport.players.sortedBy { it.team.rank }
                    )
                }

                else -> {
                    sport.copy(
                        players = sport.players.sortedByDescending { it.team.rank }
                    )
                }
            }
        }
    }
}