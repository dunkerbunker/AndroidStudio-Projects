package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init database
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "scripts_table"
        ).build()

        var add = findViewById<Button>(R.id.add)

        add.setOnClickListener {
            val intent = Intent(this, CreateScript::class.java)
            startActivity(intent)
        }
    }

    // function to update the recycler view
    fun setRecycler() {
        // get all data
        recycler_view.adapter = Adapter(DataObject.getAllData())
        // set the layout manager aka update
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}