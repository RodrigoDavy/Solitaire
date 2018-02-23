package com.example.rodrigo.solitaire

class Table internal constructor(){
    val deck = Deck()
    var deckPosition = 0

    val upperStack = arrayOf(ArrayList<Card>(), ArrayList<Card>(),
            ArrayList<Card>(), ArrayList<Card>())

    val lowerStack = arrayOf(ArrayList<Card>() ,ArrayList<Card>(), ArrayList<Card>(),
            ArrayList<Card>(), ArrayList<Card>(), ArrayList<Card>(), ArrayList<Card>())

    init {
        deck.shuffle()

        for(i in Array(lowerStack.size,{x -> x})) {
            for(j in Array(i+1,{x -> x})) {
                lowerStack[i].add(deck.cards.removeAt(0))
            }
        }
    }

    fun nextDeck() : Card? {
        deckPosition++

        if(deckPosition>=deck.cards.size) {
            deckPosition = 0
            return null
        }

        return deck.cards[deckPosition]
    }
}
