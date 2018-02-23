package com.example.rodrigo.solitaire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val deck = Deck()

        val textView = findViewById<TextView>(R.id.console)
        var str = ""

        deck.shuffle()

        for(card in deck.cards) {
            val suit : String

            when(card.suit) {
                Card.SUIT_DIAMONDS -> suit = "♦"
                Card.SUIT_CLUBS -> suit = "♣"
                Card.SUIT_HEARTS -> suit = "♥"
                Card.SUIT_SPADES -> suit = "♠"
                else -> suit = "ERROR"
            }

            str += card.number.toString() + " - " + suit + "\n"
        }

        textView.text = str
    }
}
