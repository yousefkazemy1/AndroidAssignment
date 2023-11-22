package ir.miare.androidcodechallenge.domain.mapper

import ir.miare.androidcodechallenge.domain.model.Player
import ir.miare.androidcodechallenge.domain.model.Sport

fun List<Sport>.convertToPlayersList(): List<Player> = ArrayList<Player>().apply {
    this@convertToPlayersList.forEach { sport ->
        sport.players.forEach { player ->
            add(
                player.copy(
                    id = player.id,
                    name = player.name,
                    team = player.team,
                    totalGoal = player.totalGoal
                )
            )
        }
    }
}