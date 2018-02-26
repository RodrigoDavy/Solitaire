package com.example.rodrigo.solitaire

class Table internal constructor(){
    private val deck = Deck()
    private var deckPosition = 0

    val upperStack = arrayOf(ArrayList<Card>(), ArrayList<Card>(),
            ArrayList<Card>(), ArrayList<Card>())

    val lowerStack = arrayOf(ArrayList<Card>() ,ArrayList<Card>(), ArrayList<Card>(),
            ArrayList<Card>(), ArrayList<Card>(), ArrayList<Card>(), ArrayList<Card>())
    val hiddenCards = arrayOf(0,0,0,0,0,0,0)

    companion object {
        const val MOVE_DECK_TO_LOWER = 0
        const val MOVE_DECK_TO_UPPER = 1
        const val MOVE_LOWER_TO_UPPER = 2
        const val MOVE_LOWER_TO_LOWER = 3
        const val MOVE_UPPER_TO_LOWER = 4
    }

    init {
        deck.shuffle()

        for(i in Array(lowerStack.size,{x -> x})) {
            for(j in Array(i+1,{x -> x})) {
                lowerStack[i].add(deck.cards.removeAt(0))
            }
        }

        for(i in lowerStack.indices) {
            hiddenCards[i] = lowerStack[i].size - 1
        }
    }

    fun getCardFromDeck() : Card? {
        if((deckPosition<0) ||
                (deckPosition>=deck.cards.size)) {
            return null
        }

        return deck.cards[deckPosition]
    }

    fun nextCardFromDeck() : Card? {
        deckPosition++

        if(deckPosition>=deck.cards.size) {
            deckPosition = -1
        }

        return getCardFromDeck()
    }

    fun moveLowerLower(origin: Int, destination: Int) {
        val bellowCard = lowerStack[destination][lowerStack[destination].size-1]
        val aboveCard = lowerStack[origin][lowerStack[origin].size-1]

        if(isMoveValid(bellowCard,aboveCard, MOVE_LOWER_TO_LOWER)) {
            lowerStack[destination].add(lowerStack[origin].removeAt(lowerStack[origin].size - 1))
            hiddenCards[origin]--
        }
    }

    fun moveDeckLower(destination: Int) {
        if(deckPosition<deck.cards.size) {
            val bellowCard = deck.cards[deckPosition]
            val aboveCard = lowerStack[destination][lowerStack.size - 1]

            if (isMoveValid(aboveCard, bellowCard, MOVE_DECK_TO_LOWER)) {
                lowerStack[destination].add(deck.cards.removeAt(deckPosition))
            }
        }
    }

    private fun isColorDifferent(card1: Card, card2: Card): Boolean {
        if(card1.suit == card2.suit) {
            return false
        }

        when(card1.suit) {
            Card.SUIT_DIAMONDS -> if(card2.suit == Card.SUIT_HEARTS) return false
            Card.SUIT_CLUBS -> if(card2.suit == Card.SUIT_SPADES) return false
            Card.SUIT_HEARTS -> if(card2.suit == Card.SUIT_DIAMONDS) return false
            Card.SUIT_SPADES -> if(card2.suit == Card.SUIT_CLUBS) return false
        }

        return true
    }

    private fun isMoveValid(cardAbove: Card?,cardBellow: Card?,moveType: Int): Boolean {
        when(moveType){
            MOVE_DECK_TO_LOWER, MOVE_UPPER_TO_LOWER, MOVE_LOWER_TO_LOWER -> {
                if(cardBellow==null) return false

                if(cardAbove==null) return true

                if(isColorDifferent(cardAbove,cardBellow) &&
                        (cardAbove.number==(cardBellow.number+1))) {
                    return true
                }
            }
            MOVE_DECK_TO_UPPER, MOVE_LOWER_TO_UPPER -> {
                if(cardAbove==null)
                    return false

                if(cardBellow==null) {
                    if (cardAbove.number == 1) {
                        return true
                    }
                    return false
                }

                if(cardAbove.suit!=cardBellow.suit) return false

                if(cardAbove.number==(cardBellow.number+1)) return true

            }
        }
        return false
    }
}
