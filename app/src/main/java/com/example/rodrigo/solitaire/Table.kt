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
        const val MOVE_UPPER_TO_UPPER = 5
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
        val aboveCard : Card? = if(lowerStack[origin].size > 0) {
            lowerStack[origin][lowerStack[origin].size - 1]
        }else{
            null
        }

        val bellowCard : Card? = if(lowerStack[destination].size > 0) {
            lowerStack[destination][lowerStack[destination].size - 1]
        }else{
            null
        }

        if(isMoveValid(aboveCard,bellowCard, MOVE_LOWER_TO_LOWER)) {
            lowerStack[destination].add(lowerStack[origin].removeAt(lowerStack[origin].size - 1))

            if(lowerStack[origin].size == (hiddenCards[origin]))
                hiddenCards[origin]--
        }
    }

    fun moveLowerUpper(origin: Int, destination: Int) {
        val aboveCard : Card? = if(lowerStack[origin].size > 0) {
            lowerStack[origin][lowerStack[origin].size - 1]
        }else{
            null
        }
        val bellowCard : Card? = if(upperStack[destination].size>0) {
            upperStack[destination][upperStack[destination].size-1]
        }else{
            null
        }

        if(isMoveValid(aboveCard,bellowCard, MOVE_LOWER_TO_UPPER)) {
            upperStack[destination].add(lowerStack[origin].removeAt(lowerStack[origin].size - 1))

            if(lowerStack[origin].size == (hiddenCards[origin]))
                hiddenCards[origin]--
        }
    }

    fun moveDeckLower(destination: Int) {
        if(deckPosition<deck.cards.size) {
            val aboveCard = deck.cards[deckPosition]
            val bellowCard = if(lowerStack[destination].size > 0) {
                lowerStack[destination][lowerStack[destination].size - 1]
            }else{
                null
            }

            if (isMoveValid(aboveCard, bellowCard, MOVE_DECK_TO_LOWER)) {
                lowerStack[destination].add(deck.cards.removeAt(deckPosition))
            }
        }
    }

    fun moveDeckUpper(destination: Int) {
        if(deckPosition<deck.cards.size) {
            val aboveCard = deck.cards[deckPosition]
            val bellowCard : Card? = if(upperStack[destination].size>0) {
                upperStack[destination][upperStack[destination].size - 1]
            }else{
                null
            }

            if (isMoveValid(aboveCard, bellowCard, MOVE_DECK_TO_UPPER)) {
                upperStack[destination].add(deck.cards.removeAt(deckPosition))
            }
        }
    }

    fun moveUpperLower(origin: Int, destination: Int) {
        val aboveCard = if(upperStack[origin].size>0)
            upperStack[origin][upperStack[origin].size-1]
        else
            null

        val bellowCard = if(lowerStack[destination].size>0)
            lowerStack[destination][lowerStack[destination].size-1]
        else
            null

        if(isMoveValid(aboveCard,bellowCard, MOVE_UPPER_TO_LOWER)) {
            lowerStack[destination].add(upperStack[origin].removeAt(upperStack[origin].size-1))
        }
    }

    fun moveUpperUpper(origin: Int, destination: Int) {
        val aboveCard = if(upperStack[origin].size>0)
            upperStack[origin][upperStack[origin].size-1]
        else
            null

        val bellowCard = if(upperStack[destination].size>0)
            upperStack[destination][upperStack[destination].size-1]
        else
            null

        if(isMoveValid(aboveCard,bellowCard, MOVE_UPPER_TO_UPPER)) {
            upperStack[destination].add(upperStack[origin].removeAt(upperStack[origin].size-1))
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
                if(cardAbove==null) return false

                if(cardBellow==null) return true

                if(isColorDifferent(cardAbove,cardBellow) &&
                        (cardAbove.number==(cardBellow.number-1))) {
                    return true
                }
            }
            MOVE_DECK_TO_UPPER, MOVE_LOWER_TO_UPPER, MOVE_UPPER_TO_UPPER -> {
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
