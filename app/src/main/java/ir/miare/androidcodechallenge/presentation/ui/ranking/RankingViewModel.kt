package ir.miare.androidcodechallenge.presentation.ui.ranking

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.domain.model.Sport
import ir.miare.androidcodechallenge.domain.usecase.GetSportDataUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortUseCase
import ir.miare.androidcodechallenge.presentation.model.MessageUI
import ir.miare.androidcodechallenge.presentation.model.RankUI
import ir.miare.androidcodechallenge.presentation.mapper.mapToRankUIDataList
import ir.miare.androidcodechallenge.presentation.util.SortType
import ir.miare.androidcodechallenge.util.Event
import ir.miare.androidcodechallenge.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val sportDataUseCase: GetSportDataUseCase,
    private val sortUseCase: SortUseCase,
) : ViewModel() {

    // Sport list data without sort
    private var sportListData: List<Sport> = emptyList()

    private val _sportList: MutableLiveData<List<RankUI>> = MutableLiveData(emptyList())
    val sportList: LiveData<List<RankUI>> = _sportList

    private val _progressState: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressState: LiveData<Boolean> = _progressState

    private val _message: MutableLiveData<Event<MessageUI>> = MutableLiveData(Event(MessageUI()))
    val message: LiveData<Event<MessageUI>> = _message

    fun startGettingSportData() {
        viewModelScope.launch(Dispatchers.IO) {
            getSportData()
        }
    }

    suspend fun getSportData() {
        _progressState.postValue(true)
        val result = sportDataUseCase()
        if (result is Result.Success) {
            _progressState.postValue(false)
            sportListData = result.data
            _sportList.postValue(result.data.mapToRankUIDataList())
        } else {
            _message.postValue(Event(MessageUI(id = R.string.error_in_fetching_data)))
        }
    }

    fun sortSport(sortType: SortType) {
        if (sportListData.isEmpty()) {
            _message.postValue(Event(MessageUI(id = R.string.message_data_not_loaded)))
            return
        }
        viewModelScope.launch(Dispatchers.Main) {
            when (sortType) {
                SortType.LEAGUE_AND_TEAM -> {
                    _sportList.postValue(
                        sortUseCase.byLeagueRank.invoke(
                            sportDataList = sportListData
                        ).mapToRankUIDataList()
                    )
                }

                SortType.MOST_AVERAGE_GOALS -> {
                    _sportList.postValue(
                        sortUseCase.byMostAverageGoals.invoke(
                            sportDataList = sportListData
                        ).mapToRankUIDataList()
                    )
                }

                SortType.MOST_GOALS -> {
                    _sportList.postValue(
                        sortUseCase.byMostGoals.invoke(
                            sportDataList = sportListData
                        ).mapToRankUIDataList(showTotalGoal = true)
                    )
                }

                else -> {
                    _sportList.postValue(sportListData.mapToRankUIDataList())
                }
            }
        }
    }
}