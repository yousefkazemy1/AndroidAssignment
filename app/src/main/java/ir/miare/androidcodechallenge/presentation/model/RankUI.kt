package ir.miare.androidcodechallenge.presentation.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class RankUI(
    val league: LeagueUI? = null,
    val player: PlayerUI? = null
)

data class LeagueUI(
    val name: String,
    val country: String,
    val rank: Int,
    val totalMatches: Int
)

data class PlayerUI(
    val playerName: String,
    val teamName: String,
    val teamRank: Int,
    val totalGoal: Int,
    val showTotalGoal: Boolean = false
): Serializable