package com.example.rodrigo.solitaire

import java.util.ArrayList

class Deck internal constructor() {

    val cards = ArrayList<Card>()

    init {
        val numbers = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        val suits = intArrayOf(SUIT_DIAMONDS, SUIT_CLUBS, SUIT_HEARTS, SUIT_SPADES)

        for (suit in suits) {
            for (number in numbers) {
                cards.add(Card(number, suit))
            }
        }
    }

    companion object {
        val SUIT_DIAMONDS = 0
        val SUIT_CLUBS = 1
        val SUIT_HEARTS = 2
        val SUIT_SPADES = 3
    }
}
