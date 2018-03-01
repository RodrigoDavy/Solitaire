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

    private fun printUpper() {
        val upperStack = arrayOf(
            findViewById<TextView>(R.id.upper1),
            findViewById<TextView>(R.id.upper2),
            findViewById<TextView>(R.id.upper3),
            findViewById<TextView>(R.id.upper4))

        val list = table.upperStack

        for(i in list.indices) {
            if(list[i].size>0) {
                val num = list[i][list[i].size-1].number
                val suit = list[i][list[i].size-1].suit

                upperStack[i].setText(num.toString() + " - " + suit.toString())
            }else{
                upperStack[i].setText("")
            }
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

    fun selectUpper(view: View) {
        val upperStack = arrayOf(
                findViewById<TextView>(R.id.upper1),
                findViewById<TextView>(R.id.upper2),
                findViewById<TextView>(R.id.upper3),
                findViewById<TextView>(R.id.upper4))

        var n = 0

        while(n<4) {
            if((view as TextView)==upperStack[n]) {
                break
            }
            n++
        }

        if(selected<0) {
            view.setBackgroundResource(android.R.color.holo_blue_dark)

            if(n<4) {
                selected = n + 9
            }
        }else{
            if(n<4) {
                if(selected>8) {
                    upperStack[selected-9].setBackgroundResource(android.R.color.white)
                    //TODO upper to upper move
                }else if(selected==8) {
                    table.moveDeckUpper(n)
                    printUpper()
                    printDeck()

                    findViewById<View>(R.id.deck).setBackgroundResource(android.R.color.white)
                }else{
                    table.moveLowerUpper(selected,n)
                    printLower()
                    printUpper()

                    val linearLayout = findViewById<LinearLayout>(R.id.lower_stack)

                    linearLayout.getChildAt(selected).setBackgroundResource(android.R.color.white)
                }
            }

            selected = -1
        }
    }

    fun selectLower(view: View) {
        val linearLayout = findViewById<LinearLayout>(R.id.lower_stack)

        var n = 0

        while(n<7) {
            if(view==linearLayout.getChildAt(n)) {
                break
            }
            n++
        }

        if(selected<0) {
            view.setBackgroundResource(android.R.color.holo_blue_dark)

            if (n < 7) {
                selected = n
            }
        }else if(selected > 8) {
            //TODO lower to upper
        }else{
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
