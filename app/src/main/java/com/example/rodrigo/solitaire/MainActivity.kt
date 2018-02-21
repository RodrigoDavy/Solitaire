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

        for(card in deck.cards) {
            val suit : String

            when(card.suit) {
                Deck.SUIT_DIAMONDS -> suit = "♦"
                Deck.SUIT_CLUBS -> suit = "♣"
                Deck.SUIT_HEARTS -> suit = "♥"
                Deck.SUIT_SPADES -> suit = "♠"
                else -> suit = "ERROR"
            }

            str += card.number.toString() + " - " + suit + "\n"
        }

        textView.setText(str)
    }
}
