package com.example.rodrigo.solitaire

class Table internal constructor(){
    val deck = Deck()
    var deckPosition = 0

    val upperStack = arrayOf(ArrayList<Card>(), ArrayList<Card>(),
            ArrayList<Card>(), ArrayList<Card>())

    val lowerStack = arrayOf(ArrayList<Card>() ,ArrayList<Card>(), ArrayList<Card>(),
            ArrayList<Card>(), ArrayList<Card>(), ArrayList<Card>(), ArrayList<Card>())

    companion object {
        val MOVE_DECK_TO_LOWER = 0
        val MOVE_DECK_TO_UPPER = 1
        val MOVE_LOWER_TO_UPPER = 2
        val MOVE_UPPER_TO_LOWER = 3
    }

    init {
        deck.shuffle()

        for(i in Array(lowerStack.size,{x -> x})) {
            for(j in Array(i+1,{x -> x})) {
                lowerStack[i].add(deck.cards.removeAt(0))
            }
        }
    }

    fun nextFromDeck() : Card? {
        deckPosition++

        if(deckPosition>=deck.cards.size) {
            deckPosition = 0
            return null
        }

        return deck.cards[deckPosition]
    }

    fun removeFromDeck(): Card?{
        if(deckPosition>=deck.cards.size) {
            deckPosition = 0
            return null
        }

        return deck.cards.removeAt(deckPosition)
    }

    fun isColorDifferent(card1: Card, card2: Card): Boolean {
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

    fun isMoveValid(cardAbove: Card?,cardBellow: Card?,moveType: Int): Boolean {
        when(moveType){
            MOVE_DECK_TO_LOWER, MOVE_UPPER_TO_LOWER -> {
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
