package com.example.mvrtousd

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // hide action bar
        supportActionBar?.hide()

        // get the text view
        val tvAnswer = findViewById<TextView>(R.id.tvAnswer)
        val button = findViewById<Button>(R.id.button)
        val etMVR = findViewById<EditText>(R.id.etMVR)

        // onclick listener for the button
        button.setOnClickListener {
            // get the value from the edit text
            val mvr = etMVR.text.toString().toDouble()
            // calculate the answer to 2 decimal places
            val answer = mvr / 15.6
            // set the answer to the text view
            tvAnswer.text = "The answer is $answer"
        }
    }
}