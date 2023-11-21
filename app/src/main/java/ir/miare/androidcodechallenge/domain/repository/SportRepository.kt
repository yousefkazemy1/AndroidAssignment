package ir.miare.androidcodechallenge.domain.repository

import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.util.Result

interface SportRepository {
    suspend fun fetchSportData(): Result<List<Sport>>
}