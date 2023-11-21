package ir.miare.androidcodechallenge.presentation.ui.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.databinding.ItemLeagueBinding
import ir.miare.androidcodechallenge.databinding.ItemPlayerBinding
import ir.miare.androidcodechallenge.presentation.model.PlayerUI
import ir.miare.androidcodechallenge.presentation.model.RankUI

class RankingAdapter(
    private val actionClick: (playerUI: PlayerUI) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var rankList: List<RankUI> = emptyList()
        set(value) {
            field = value
        }

    companion object {
        const val VIEW_TYPE_LEAGUE = 1
        const val VIEW_TYPE_PLAYER = 2
    }

    private inner class LeagueViewHolder(val view: ItemLeagueBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    private inner class PlayerViewHolder(val view: ItemPlayerBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            view.root.setOnClickListener {
                rankList[adapterPosition].player?.let {
                    actionClick(it)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val rankUI = rankList[position]
        return if (rankUI.league != null) {
            VIEW_TYPE_LEAGUE
        } else {
            VIEW_TYPE_PLAYER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LEAGUE) {
            val leagueViewHolder =
                ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LeagueViewHolder(leagueViewHolder)
        } else {
            val playerViewHolder =
                ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PlayerViewHolder(playerViewHolder)
        }
    }

    override fun getItemCount() = rankList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeagueViewHolder) {
            bindLeagueViewHolder(holder, position)
        } else if (holder is PlayerViewHolder) {
            bindPlayerViewHolder(holder, position)
        }
    }

    private fun bindLeagueViewHolder(holder: LeagueViewHolder, position: Int) {
        rankList[position].league?.let {
            holder.view.leagueName.text = it.name
            holder.view.leagueCountry.text = it.country
        }
    }

    private fun bindPlayerViewHolder(holder: PlayerViewHolder, position: Int) {
        rankList[position].player?.let {
            holder.view.playerName.text = it.playerName
            holder.view.teamName.text = it.teamName

            if (it.showTotalGoal) {
                holder.view.rank.text = it.totalGoal.toString()
            } else {
                holder.view.rank.text = it.teamRank.toString()
            }
        }
    }
}