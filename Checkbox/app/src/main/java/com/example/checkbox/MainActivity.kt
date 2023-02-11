package com.example.checkbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val checkbox1 = findViewById<CheckBox>(R.id.checkBox)
        val checkbox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkBox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkBox4)

        button.setOnClickListener {
            val msg = StringBuilder()

            msg.append("Languages known are:\n");
            if(checkbox1.isChecked)
                msg.append("${checkbox1.text}\n")
            if(checkbox2.isChecked)
                msg.append("${checkbox2.text}\n")
            if(checkbox3.isChecked)
                msg.append("${checkbox3.text}\n")
            if(checkbox4.isChecked)
                msg.append("${checkbox4.text}\n")

            // if no checkbox is checked
            if(!checkbox1.isChecked && !checkbox2.isChecked && !checkbox3.isChecked && !checkbox4.isChecked)
                msg.clear()
                msg.append("Go learn something idiot")

            Toast.makeText(this, msg.toString(), Toast.LENGTH_LONG).show()
        }
    }
}