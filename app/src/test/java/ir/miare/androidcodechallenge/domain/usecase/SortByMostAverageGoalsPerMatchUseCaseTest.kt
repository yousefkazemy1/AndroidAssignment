package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.generateSportFakeAscending
import ir.miare.androidcodechallenge.generateSportFakeDescending
import ir.miare.androidcodechallenge.util.SortOrder
import org.junit.Assert
import org.junit.Test

class SortByMostAverageGoalsPerMatchUseCaseTest {

    @Test
    fun `sort by most average goals per match, order descending`() {
        val unSortedData = generateSportFakeAscending()

        val sortedData = SortByMostAverageGoalsPerMatchUseCase().invoke(
            sportDataList = unSortedData, sortOrder = SortOrder.DESC
        )

        val expectedData = generateSportFakeDescending().map { sport ->
            sport.copy(
                id = sport.id, league = sport.league, players = sport.players.reversed()
            )
        }

        val result = sortedData == expectedData
        Assert.assertTrue(result)
    }

    @Test
    fun `sort by most average goals per match, order ascending`() {
        val unSortedData = generateSportFakeDescending()

        val sortedData = SortByMostAverageGoalsPerMatchUseCase().invoke(
            sportDataList = unSortedData, sortOrder = SortOrder.ASC
        )

        val expectedData = generateSportFakeAscending().map { sport ->
            sport.copy(
                id = sport.id, league = sport.league, players = sport.players.reversed()
            )
        }

        val result = sortedData == expectedData
        Assert.assertTrue(result)
    }
}