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
        //  1 MVR = 0.065 US$
        //  1 MVR = 5.30 INR
        //  1 MVR = 0.089 Singapore Dollar
        //  I MVR = 0.82 UAE Dirham
        val items = arrayOf("MVR", "USD", "INR", "Singapore Dollar", "UAE Dirham")
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
                // set result with 2 decimal places
                tv_result.text = "%.2f".format(result)
            }
        }
    }

    private fun convert(currency1: String, currency2: String, amount: String): Any {
        when (currency1) {
            "MVR" -> {
                when (currency2) {
                    "USD" -> {
                        return amount.toDouble() * 0.065
                    }
                    "INR" -> {
                        return amount.toDouble() * 5.30
                    }
                    "Singapore Dollar" -> {
                        return amount.toDouble() * 0.089
                    }
                    "UAE Dirham" -> {
                        return amount.toDouble() * 0.82
                    }
                    else -> {
                        return amount.toDouble()
                    }
                }
            }
            "USD" -> {
                when (currency2) {
                    "MVR" -> {
                        return amount.toDouble() * 15.38
                    }
                    "INR" -> {
                        return amount.toDouble() * 81.92
                    }
                    "Singapore Dollar" -> {
                        return amount.toDouble() * 1.36
                    }
                    "UAE Dirham" -> {
                        return amount.toDouble() * 12.67
                    }
                    else -> {
                        return amount.toDouble()
                    }
                }
            }
            "INR" -> {
                when (currency2) {
                    "MVR" -> {
                        return amount.toDouble() * 0.19
                    }
                    "USD" -> {
                        return amount.toDouble() * 0.012
                    }
                    "Singapore Dollar" -> {
                        return amount.toDouble() * 0.016
                    }
                    "UAE Dirham" -> {
                        return amount.toDouble() * 0.15
                    }
                    else -> {
                        return amount.toDouble()
                    }
                }
            }
            "Singapore Dollar" -> {
                when (currency2) {
                    "MVR" -> {
                        return amount.toDouble() * 11.22
                    }
                    "USD" -> {
                        return amount.toDouble() * 0.73
                    }
                    "INR" -> {
                        return amount.toDouble() * 62.50
                    }
                    "UAE Dirham" -> {
                        return amount.toDouble() * 8.93
                    }
                    else -> {
                        return amount.toDouble()
                    }
                }
            }
            "UAE Dirham" -> {
                when (currency2) {
                    "MVR" -> {
                        return amount.toDouble() * 1.22
                    }
                    "USD" -> {
                        return amount.toDouble() * 0.079
                    }
                    "INR" -> {
                        return amount.toDouble() * 6.50
                    }
                    "Singapore Dollar" -> {
                        return amount.toDouble() * 0.11
                    }
                    else -> {
                        return amount.toDouble()
                    }
                }
            }
            else -> {
                return amount.toDouble()
            }
        }
        return 0
    }
}