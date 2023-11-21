package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.getSportFakeDate
import ir.miare.androidcodechallenge.util.SortOrder
import org.junit.Assert.*

import org.junit.Test

class SortByLeagueAndTeamRankingUseCaseTest {
    @Test
    fun `sort by ascending`() {
        val unSortedData = getSportFakeDate()

        val sortedData = SortByLeagueAndTeamRankingUseCase().invoke(
            sportDataList = unSortedData,
            sortOrder = SortOrder.ASC
        )

        println(unSortedData)
        println(sortedData)
    }
}