package com.example.logintutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get textview and button from layout
        val etUsername = findViewById<TextView>(R.id.etUsername)
        val etPassword = findViewById<TextView>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnClear = findViewById<Button>(R.id.btnClear)

        // set on-click listener
        btnLogin.setOnClickListener {
            // if username and password is exists
            if (etUsername.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
                // toast login info

                Toast.makeText(this, "Going to next page in 3", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Going to next page in 2", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Going to next page in 1", Toast.LENGTH_SHORT).show()

                // intent to go back to the first activity
                var intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                // toast error message
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnClear.setOnClickListener {..
            etUsername.setText("")
            etPassword.setText("")
        }
    }
}
