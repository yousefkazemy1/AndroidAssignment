package ir.miare.androidcodechallenge.domain.model

data class League(
    val id: Int = 0,
    val name: String,
    val country: String,
    val rank: Int,
    val totalMatches: Int
)