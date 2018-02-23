package com.example.rodrigo.solitaire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    val table = Table()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lowerStack = table.lowerStack

        val linearLayout = findViewById<LinearLayout>(R.id.lower_stack)

        for(x in Array(7,{i -> i})) {
            printLower(linearLayout.getChildAt(x) as TextView,lowerStack[x])
        }
    }

    fun printLower(textView: TextView,cards: ArrayList<Card>) {
        var str = ""

        for(card in cards) {
            str += card.number.toString() + " - " + card.suit.toString() + "\n"
        }

        textView.setText(str)
    }

    fun next(view: View) {
        val card = table.nextDeck()
        val textView = findViewById<TextView>(R.id.console)

        if(card==null) {
            textView.setText("")
        }else{
            val str = card.number.toString() + " - " + card.suit.toString()
            textView.setText(str)
        }
    }
}
