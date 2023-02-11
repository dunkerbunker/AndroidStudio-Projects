package com.example.checkboxcalculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val checkbox1 = findViewById<CheckBox>(R.id.checkBox)
        val checkbox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkBox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkBox4)
        val total = findViewById<TextView>(R.id.total)

        button.setOnClickListener {
            if(!checkbox1.isChecked && !checkbox2.isChecked && !checkbox3.isChecked && !checkbox4.isChecked) {
                total.text = "You have no groceries"
                total.visibility = TextView.VISIBLE
            } else {
                var sum = 0
                if(checkbox1.isChecked)
                    sum += 5
                if(checkbox2.isChecked)
                    sum += 2
                if(checkbox3.isChecked)
                    sum += 20
                if(checkbox4.isChecked)
                    sum += 30
                total.text = "Total: $$sum"
                total.visibility = TextView.VISIBLE
            }

        }
    }
}