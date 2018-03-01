package com.example.rodrigo.solitaire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val table = Table()
    private var selected = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printLower()
    }

    private fun printDeck() {
        val textView = findViewById<TextView>(R.id.deck)
        val card = table.getCardFromDeck()

        if(card==null) {
            textView.text = ""
        }else{
            val str = card.number.toString() + " - " + card.suit.toString()
            textView.text = str
        }
    }

    private fun printLower() {
        val linearLayout = findViewById<LinearLayout>(R.id.lower_stack)
        val lowerStack = table.lowerStack

        for(x in 0..6) {
            val textView = linearLayout.getChildAt(x) as TextView
            val cards = lowerStack[x]

            var str = ""

            for(i in 0 until cards.size) {
                if(i<table.hiddenCards[x]) {
                    str += "? - ?\n"
                }else{
                    str += cards[i].number.toString() + " - " + cards[i].suit.toString() + "\n"
                }
            }

            textView.setText(str)
        }
    }

    fun selectDeck(view: View) {
        if(selected<0) {
            view.setBackgroundResource(android.R.color.holo_blue_dark)
            selected = 8
        }else{
            view.setBackgroundResource(android.R.color.white)
            selected = -1
        }
    }

    fun selectLower(view: View) {
        val linearLayout = findViewById<LinearLayout>(R.id.lower_stack)

        if(selected<0) {
            view.setBackgroundResource(android.R.color.holo_blue_dark)

            var n = 0

            while(n<7) {
                if(view==linearLayout.getChildAt(n)) {
                    break
                }
                n++
            }

            if(n<7) {
                selected = n
            }
        }else{
            var n = 0

            while(n<7) {
                if(view==linearLayout.getChildAt(n)) {
                    break
                }
                n++
            }


            if(n<7) {
                if(selected==8) {
                    table.moveDeckLower(n)
                    printLower()
                    printDeck()

                    findViewById<View>(R.id.deck).setBackgroundResource(android.R.color.white)
                }else{
                    table.moveLowerLower(selected,n)
                    printLower()

                    linearLayout.getChildAt(selected).setBackgroundResource(android.R.color.white)
                }
            }

            selected = -1
        }
    }

    fun next(view: View) {
        table.nextCardFromDeck()

        printDeck()
    }
}
