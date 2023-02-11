package com.example.bmicalculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_weight = findViewById<EditText>(R.id.et_weight)
        val et_height = findViewById<EditText>(R.id.et_height)
        val btn_calculate = findViewById<Button>(R.id.btn_calculate)
        val tv_classification = findViewById<TextView>(R.id.tv_classification)
        val tv_bmi = findViewById<TextView>(R.id.tv_bmi)
        val card = findViewById<ConstraintLayout>(R.id.constraintLayout)

        // hide
        card.visibility = ConstraintLayout.GONE
        tv_classification.visibility = TextView.GONE
        tv_bmi.visibility = TextView.GONE

        btn_calculate.setOnClickListener {
            val weight = et_weight.text.toString()
            val height = et_height.text.toString()

            if (weight.isEmpty() || height.isEmpty()) {
                Toast.makeText(this, "Please enter weight and height", Toast.LENGTH_SHORT).show()
            } else {
                val bmi = weight.toDouble() / (height.toDouble() * height.toDouble())
                // one decimal place
                tv_bmi.text = "Your BMI is: " + "%.1f".format(bmi)

                if (bmi < 18.5) {
                    tv_classification.text = "Under Weight"
                    tv_classification.setTextColor(getColor(R.color.red))
                } else if (bmi < 25) {
                    tv_classification.text = "Normal"
                    tv_classification.setTextColor(getColor(R.color.green))
                } else if (bmi < 30) {
                    tv_classification.text = "Over Weight"
                    tv_classification.setTextColor(getColor(R.color.red))
                } else if (bmi < 35) {
                    tv_classification.text = "Obesity (Class I)"
                    tv_classification.setTextColor(getColor(R.color.red))
                } else if (bmi < 40) {
                    tv_classification.text = "Obesity (Class II)"
                    tv_classification.setTextColor(getColor(R.color.red))
                } else {
                    tv_classification.text = "Extreme Obesity"
                    tv_classification.setTextColor(getColor(R.color.red))
                }

                // show
                card.visibility = ConstraintLayout.VISIBLE
                tv_classification.visibility = TextView.VISIBLE
                tv_bmi.visibility = TextView.VISIBLE
            }
        }
    }
}