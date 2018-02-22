package com.example.rodrigo.solitaire

data class Card(val number: Int,val suit: Int) {
    companion object {
        val SUIT_DIAMONDS = 0
        val SUIT_CLUBS = 1
        val SUIT_HEARTS = 2
        val SUIT_SPADES = 3
    }
}