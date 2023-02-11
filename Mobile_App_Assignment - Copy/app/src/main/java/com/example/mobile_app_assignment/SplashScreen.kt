/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file only brings up the SplashScreen and goes to the MainActivity
    * This file will be run when the app is opened
*/

package com.example.mobile_app_assignment

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