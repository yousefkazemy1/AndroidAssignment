package ir.miare.androidcodechallenge.data.repository

import ir.miare.androidcodechallenge.data.mapper.mapToSportDataList
import ir.miare.androidcodechallenge.data.remote.SportApi
import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.domain.repository.SportRepository
import ir.miare.androidcodechallenge.util.ErrorCause
import ir.miare.androidcodechallenge.util.ErrorResult
import ir.miare.androidcodechallenge.util.Result

class SportRepositoryImpl(
    private val sportApi: SportApi,
) : SportRepository {
    override suspend fun fetchSportData(): Result<List<Sport>> {
        return try {
            sportApi.getData().execute().let { response ->
                if (response.isSuccessful) {
                    response.body()?.let { sportResponses ->
                        Result.Success(sportResponses.mapToSportDataList())
                    } ?: Result.Error(
                        ErrorResult(
                            errorCode = response.code(), errorCause = ErrorCause.API
                        )
                    )
                } else {
                    Result.Error(
                        ErrorResult(
                            errorCode = response.code(), errorCause = ErrorCause.API
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Result.Error(
                ErrorResult(errorCause = ErrorCause.EXCEPTION)
            )
        }
    }
}