package ir.miare.androidcodechallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.miare.androidcodechallenge.data.remote.SportApi
import ir.miare.androidcodechallenge.data.repository.SportRepositoryImpl
import ir.miare.androidcodechallenge.domain.repository.SportRepository
import ir.miare.androidcodechallenge.domain.usecase.GetSportDataUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortByLeagueAndTeamRankingUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortByMostAverageGoalsPerMatchUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortByMostGoalsUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortUseCase
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module()
class ViewModelModule {
    @Provides
    fun provideSportApi(
        retrofit: Retrofit,
    ): SportApi = retrofit.create(SportApi::class.java)

    @Provides
    fun provideSportRepository(
        sportApi: SportApi,
    ): SportRepository = SportRepositoryImpl(sportApi = sportApi)

    @Provides
    fun provideGetSportDataUseCase(
        sportRepository: SportRepository,
    ): GetSportDataUseCase = GetSportDataUseCase(sportRepository = sportRepository)

    @Provides
    fun provideSortUseCase(): SortUseCase = SortUseCase(
        byLeagueRank = SortByLeagueAndTeamRankingUseCase(),
        byMostGoals = SortByMostGoalsUseCase(),
        byMostAverageGoals = SortByMostAverageGoalsPerMatchUseCase()
    )
}