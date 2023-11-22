package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.mapper.convertToPlayersList
import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.generateSportFakeAscending
import ir.miare.androidcodechallenge.generateSportFakeDescending
import ir.miare.androidcodechallenge.util.SortOrder
import org.junit.Assert
import org.junit.Test

class SortByMostGoalsUseCaseTest {

    @Test
    fun `sort by most goals, order descending`() {
        val unSortedData = generateSportFakeAscending()

        val sortedData = SortByMostGoalsUseCase().invoke(
            sportDataList = unSortedData, sortOrder = SortOrder.DESC
        )

        val expectedData = generateSportFakeDescending().convertToPlayersList().let {
            listOf(Sport(players = it))
        }

        val result = sortedData == expectedData
        Assert.assertTrue(result)
    }

    @Test
    fun `sort by most goals, order ascending`() {
        val unSortedData = generateSportFakeDescending()

        val sortedData = SortByMostGoalsUseCase().invoke(
            sportDataList = unSortedData, sortOrder = SortOrder.ASC
        )

        val expectedData = generateSportFakeAscending().convertToPlayersList().let {
            listOf(Sport(players = it))
        }

        val result = sortedData == expectedData
        Assert.assertTrue(result)
    }
}