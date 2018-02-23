package com.example.rodrigo.solitaire

import java.util.*

class Deck internal constructor() {

    var cards = ArrayList<Card>()

    fun shuffle() {
        val random = Random()
        val tempCards = ArrayList<Card>()

        for(i in Array(cards.size,{x -> x})) {
            tempCards.add(cards.removeAt(random.nextInt(cards.size)))
        }
        
        cards = tempCards
    }

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
