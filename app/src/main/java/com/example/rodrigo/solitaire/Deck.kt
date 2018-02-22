package com.example.rodrigo.solitaire

import java.util.ArrayList

class Deck internal constructor() {

    val cards = ArrayList<Card>()

    init {
        val numbers = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        val suits = intArrayOf(Card.SUIT_DIAMONDS,Card.SUIT_CLUBS,Card.SUIT_HEARTS,Card.SUIT_SPADES)

        for (suit in suits) {
            for (number in numbers) {
                cards.add(Card(number, suit))
            }
        }
    }
}
