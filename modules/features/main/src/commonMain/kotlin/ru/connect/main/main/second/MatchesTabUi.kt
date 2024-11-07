package ru.connect.main.main.second

import androidx.compose.runtime.Immutable

@Immutable
class MatchesTabUi(
    val matched: List<Card>,
    val likedYou: List<Card>,
) {
    @Immutable
    data class Card(val id: String, val image: String)
}
