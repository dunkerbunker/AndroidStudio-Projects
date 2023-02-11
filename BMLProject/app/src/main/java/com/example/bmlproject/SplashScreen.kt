package com.example.bmlproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    // create  handler object
    // it provides a delay before the next activity is opened
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // remove action bar
        supportActionBar?.hide()

        // initialize the handler object
        handler = Handler()

        // post a delayed message to the message queue
        handler.postDelayed({
            // got to the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            // after 2 seconds
        }, 2000) // 2 secs
    }
}