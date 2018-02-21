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
            str += card.number.toString() + " - " + card.suit.toString() + "\n"
        }

        textView.setText(str)
    }
}
