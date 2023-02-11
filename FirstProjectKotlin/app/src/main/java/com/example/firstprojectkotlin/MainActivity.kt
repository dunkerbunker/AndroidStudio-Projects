package com.example.firstprojectkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btButton)

        btn.setOnClickListener {
            Toast.makeText(this, "No robux for you", Toast.LENGTH_SHORT).show()
        }
    }
}