package ir.miare.androidcodechallenge.domain.model

data class Sport(
    val id: Int = 0,
    val league: League? = null,
    val players: List<Player>
)