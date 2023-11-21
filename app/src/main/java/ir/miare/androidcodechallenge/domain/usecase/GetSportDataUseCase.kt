package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.domain.repository.SportRepository
import ir.miare.androidcodechallenge.util.Result

class GetSportDataUseCase(
    private val sportRepository: SportRepository
) {
    suspend operator fun invoke() : Result<List<Sport>> {
        return sportRepository.fetchSportData();
    }
}