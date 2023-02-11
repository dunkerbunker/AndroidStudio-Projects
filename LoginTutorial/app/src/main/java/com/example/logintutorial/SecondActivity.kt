package com.example.logintutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            // intent to go back to the first activity
            var homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }
    }
}