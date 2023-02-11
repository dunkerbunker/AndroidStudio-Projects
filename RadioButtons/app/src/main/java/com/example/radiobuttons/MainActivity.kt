package com.example.radiobuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b1 = findViewById<Button>(R.id.button)
        val rg1 = findViewById<RadioGroup>(R.id.radioGroup)

        b1.setOnClickListener {
            val msg = StringBuilder()

            msg.append("Year of study \n")

            val year = rg1.checkedRadioButtonId

            val rb1 = findViewById<RadioButton>(year)

            if (rb1 != null) {
                msg.append(rb1.text)
                Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}