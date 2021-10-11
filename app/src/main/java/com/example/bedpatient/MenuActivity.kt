package com.example.bedpatient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {

    lateinit var cardView1: CardView
    lateinit var cardView2: CardView
    lateinit var cardView3: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        cardView1 = findViewById<CardView>(R.id.CardView1)
        cardView2 = findViewById<CardView>(R.id.CardView2)
        cardView3 = findViewById<CardView>(R.id.CardView3)

        cardView1!!.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_MESSAGE,"My New Alarm")
            intent.putExtra(AlarmClock.EXTRA_HOUR,6)
            intent.putExtra(AlarmClock.EXTRA_MINUTES,30)
            intent.putExtra(AlarmClock.EXTRA_DAYS, arrayListOf(2))
            startActivity(intent)
        }

        cardView2!!.setOnClickListener {
            val intent = Intent(this,Arcticles::class.java)
            startActivity(intent)
        }

    }
}