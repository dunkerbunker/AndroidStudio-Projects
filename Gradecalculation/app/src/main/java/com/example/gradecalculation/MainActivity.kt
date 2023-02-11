package com.example.gradecalculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_One = findViewById<EditText>(R.id.et_One)
        val et_Two = findViewById<EditText>(R.id.et_Two)
        val et_Three = findViewById<EditText>(R.id.et_Three)
        val btn_submit = findViewById<Button>(R.id.btn_submit)
    }
}