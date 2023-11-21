package ir.miare.androidcodechallenge.domain.model

data class Player(
    val id: Int = 0,
    val name: String,
    val team: Team,
    val totalGoal: Int
)