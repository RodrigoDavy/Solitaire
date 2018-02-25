package com.example.rodrigo.solitaire

data class Card(val number: Int,val suit: Int) {
    companion object {
        const val SUIT_DIAMONDS = 0
        const val SUIT_CLUBS = 1
        const val SUIT_HEARTS = 2
        const val SUIT_SPADES = 3
    }
}