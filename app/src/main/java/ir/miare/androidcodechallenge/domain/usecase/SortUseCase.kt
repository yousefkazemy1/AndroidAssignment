package ir.miare.androidcodechallenge.domain.usecase

data class SortUseCase(
    val byLeagueRank: SortByLeagueAndTeamRankingUseCase,
    val byMostGoals: SortByMostGoalsUseCase,
    val byMostAverageGoals: SortByMostAverageGoalsPerMatchUseCase
)