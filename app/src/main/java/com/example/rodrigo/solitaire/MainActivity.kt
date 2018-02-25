package com.example.rodrigo.solitaire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val table = Table()
    private var selected : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lowerStack = table.lowerStack

        val linearLayout = findViewById<LinearLayout>(R.id.lower_stack)

        for(x in Array(7,{i -> i})) {
            printLower(linearLayout.getChildAt(x) as TextView,lowerStack[x])
        }
    }

    private fun printLower(textView: TextView,cards: ArrayList<Card>) {
        var str = ""

        for(i in 0 until cards.size) {
            if(i==(cards.size-1)) {
                str += cards[i].number.toString() + " - " + cards[i].suit.toString() + "\n"
            }else{
                str += "? - ?\n"
            }
        }

        textView.setText(str)
    }

    fun select(view: View) {
        if(selected==null) {
            view.setBackgroundResource(android.R.color.holo_blue_dark)
            selected = view
        }else{
            selected!!.setBackgroundResource(android.R.color.white)
            selected = null
        }
    }

    fun next(view: View) {
        val card = table.nextFromDeck()
        val textView = findViewById<TextView>(R.id.console)

        if(card==null) {
            textView.setText("")
        }else{
            val str = card.number.toString() + " - " + card.suit.toString()
            textView.setText(str)
        }
    }
}
