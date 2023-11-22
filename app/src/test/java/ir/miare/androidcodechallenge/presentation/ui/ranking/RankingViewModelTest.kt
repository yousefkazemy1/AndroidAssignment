package ir.miare.androidcodechallenge.presentation.ui.ranking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import ir.miare.androidcodechallenge.MainCoroutineRule
import ir.miare.androidcodechallenge.domain.repository.SportRepository
import ir.miare.androidcodechallenge.domain.usecase.GetSportDataUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortByLeagueAndTeamRankingUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortByMostAverageGoalsPerMatchUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortByMostGoalsUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortUseCase
import ir.miare.androidcodechallenge.generateSportFakeAscending
import ir.miare.androidcodechallenge.generateSportFakeDescending
import ir.miare.androidcodechallenge.getOrAwaitValue
import ir.miare.androidcodechallenge.presentation.mapper.mapToRankUIDataList
import ir.miare.androidcodechallenge.presentation.util.SortType
import ir.miare.androidcodechallenge.util.ErrorResult
import ir.miare.androidcodechallenge.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RankingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RankingViewModel

    private lateinit var repository: SportRepository

    private lateinit var sortUseCase: SortUseCase

    @Before
    fun setUp() {
        sortUseCase = SortUseCase(
            byLeagueRank = SortByLeagueAndTeamRankingUseCase(),
            byMostGoals = SortByMostGoalsUseCase(),
            byMostAverageGoals = SortByMostAverageGoalsPerMatchUseCase()
        )

        repository = mockk()

        val sportDataUseCase = GetSportDataUseCase(
            sportRepository = repository
        )

        viewModel = RankingViewModel(
            sortUseCase = sortUseCase, sportDataUseCase = sportDataUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get sport data successfully`() = runTest {
        coEvery {
            repository.fetchSportData()
        } returns Result.Success(generateSportFakeAscending())

        viewModel.getSportData()

        val list = viewModel.sportList.getOrAwaitValue()
        assert(list.isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get sport data with error`() = runTest {
        coEvery {
            repository.fetchSportData()
        } returns Result.Error(ErrorResult())

        viewModel.getSportData()

        val list = viewModel.sportList.getOrAwaitValue()
        val message = viewModel.message.getOrAwaitValue()
        assert(list.isEmpty() && message.getContentIfNotHandled()?.id != -1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort by league and team`() = runTest {
        val fakeData = generateSportFakeDescending()
        coEvery {
            repository.fetchSportData()
        } returns Result.Success(fakeData)

        viewModel.getSportData()

        viewModel.sortSport(sortType = SortType.LEAGUE_AND_TEAM)

        val expectedResult =
            sortUseCase.byLeagueRank.invoke(sportDataList = fakeData).mapToRankUIDataList()

        val actualResult = viewModel.sportList.getOrAwaitValue()

        val result = expectedResult == actualResult
        assertTrue(result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort by most average goals`() = runTest {
        val fakeData = generateSportFakeAscending()
        coEvery {
            repository.fetchSportData()
        } returns Result.Success(fakeData)

        viewModel.getSportData()

        viewModel.sortSport(sortType = SortType.MOST_AVERAGE_GOALS)

        val expectedResult =
            sortUseCase.byMostAverageGoals.invoke(sportDataList = fakeData).mapToRankUIDataList()

        val actualResult = viewModel.sportList.getOrAwaitValue()

        val result = expectedResult == actualResult
        assertTrue(result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort by most goals`() = runTest {
        val fakeData = generateSportFakeAscending()
        coEvery {
            repository.fetchSportData()
        } returns Result.Success(fakeData)

        viewModel.getSportData()

        viewModel.sortSport(sortType = SortType.MOST_GOALS)

        val expectedResult = sortUseCase.byMostGoals.invoke(sportDataList = fakeData)
            .mapToRankUIDataList(showTotalGoal = true)

        val actualResult = viewModel.sportList.getOrAwaitValue()

        val result = expectedResult == actualResult
        assertTrue(result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort by default`() = runTest {
        val fakeData = generateSportFakeAscending()
        coEvery {
            repository.fetchSportData()
        } returns Result.Success(fakeData)

        viewModel.getSportData()

        viewModel.sortSport(sortType = SortType.MOST_GOALS)
        viewModel.sortSport(sortType = SortType.NONE)

        val expectedResult = fakeData.mapToRankUIDataList()

        val actualResult = viewModel.sportList.getOrAwaitValue()

        val result = expectedResult == actualResult
        assertTrue(result)
    }
}