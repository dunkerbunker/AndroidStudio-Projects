package com.example.currencyconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_Currency1 = findViewById<EditText>(R.id.et_Currency1)
        val spinner_Currency1 = findViewById<Spinner>(R.id.spinner_Currency1)
        val spinner_Currency2 = findViewById<Spinner>(R.id.spinner_Currency2)
        val tv_result = findViewById<TextView>(R.id.tv_result)
        val btn_convert = findViewById<Button>(R.id.btn_convert)

        // set content of spinner and get chosen value
        val items = arrayOf("USD", "EUR", "MVR", "INR")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner_Currency1.adapter = adapter
        spinner_Currency2.adapter = adapter

        btn_convert.setOnClickListener {
            val currency1 = spinner_Currency1.selectedItem.toString()
            val currency2 = spinner_Currency2.selectedItem.toString()
            val amount = et_Currency1.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
            } else {
                // convert without api
                val result =  convert(currency1, currency2, amount)
                tv_result.text = result.toString()
            }
        }
    }

    private fun convert(currency1: String, currency2: String, amount: String): Any {
        when (currency1) {
            "USD" -> {
                when (currency2) {
                    "USD" -> {
                        return amount
                    }
                    "EUR" -> {
                        return amount.toDouble() * 0.84
                    }
                    "MVR" -> {
                        return amount.toDouble() * 15.40
                    }
                    "INR" -> {
                        return amount.toDouble() * 73.50
                    }
                }
            }
            "EUR" -> {
                when (currency2) {
                    "USD" -> {
                        return amount.toDouble() * 1.19
                    }
                    "EUR" -> {
                        return amount
                    }
                    "MVR" -> {
                        return amount.toDouble() * 18.40
                    }
                    "INR" -> {
                        return amount.toDouble() * 87.50
                    }
                }
            }
            "MVR" -> {
                when (currency2) {
                    "USD" -> {
                        return amount.toDouble() * 0.065
                    }
                    "EUR" -> {
                        return amount.toDouble() * 0.054
                    }
                    "MVR" -> {
                        return amount
                    }
                    "INR" -> {
                        return amount.toDouble() * 4.70
                    }
                }
            }
            "INR" -> {
                when (currency2) {
                    "USD" -> {
                        return amount.toDouble() * 0.014
                    }
                    "EUR" -> {
                        return amount.toDouble() * 0.011
                    }
                    "MVR" -> {
                        return amount.toDouble() * 0.21
                    }
                    "INR" -> {
                        return amount
                    }
                }
            }
        }
        return 0
    }
}