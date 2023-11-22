package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.generateSportFakeAscending
import ir.miare.androidcodechallenge.generateSportFakeDescending
import ir.miare.androidcodechallenge.util.SortOrder
import org.junit.Assert
import org.junit.Test

class SortByLeagueAndTeamRankingUseCaseTest {
    @Test
    fun `sort by leagues and teams rank, order descending`() {
        val unSortedData = generateSportFakeDescending()

        val sortedData = SortByLeagueAndTeamRankingUseCase().invoke(
            sportDataList = unSortedData, sortOrder = SortOrder.DESC
        )

        val expectedData = generateSportFakeAscending()

        val result = sortedData == expectedData
        Assert.assertTrue(result)
    }

    @Test
    fun `sort by leagues and teams rank, order ascending`() {
        val unSortedData = generateSportFakeAscending()

        val sortedData = SortByLeagueAndTeamRankingUseCase().invoke(
            sportDataList = unSortedData, sortOrder = SortOrder.ASC
        )

        val expectedData = generateSportFakeDescending()

        val result = sortedData == expectedData
        Assert.assertTrue(result)
    }
}