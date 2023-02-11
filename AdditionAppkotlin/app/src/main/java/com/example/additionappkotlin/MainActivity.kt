package com.example.additionappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numOne = findViewById<EditText>(R.id.numOne)
        val numTwo = findViewById<EditText>(R.id.numTwo)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            val enteredNumOne = numOne.text.toString()
            val enteredNumTwo = numTwo.text.toString()

            val sum = enteredNumOne.toInt() + enteredNumTwo.toInt()

            Toast.makeText(this, "The sum of $enteredNumOne and $enteredNumTwo is $sum", Toast.LENGTH_SHORT).show()
        }
    }
}