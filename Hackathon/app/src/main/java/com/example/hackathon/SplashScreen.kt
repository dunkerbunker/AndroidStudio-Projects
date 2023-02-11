package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    // create db
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // hide action bar
        supportActionBar?.hide()

        // init db instance
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "scripts_table"
        ).build()

        // get all the stored data while the splash screen is running
        GlobalScope.launch {
            DataObject.listdata = database.dao().getScripts() as MutableList<ScriptModel>
        }

        // delay the splash screen for 2 seconds and go to the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }

}