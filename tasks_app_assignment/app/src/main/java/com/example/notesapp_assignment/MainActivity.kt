package com.example.notesapp_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // Create a database
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize the database
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        // add button that intents to create card
        add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }

        // button to delete all tasks
        deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                // database method to delete all tasks
                database.dao().deleteAll()
            }
            // update the recycler view
            setRecycler()
        }
        // update the recycler view
        setRecycler()

    }
    // function to update the recycler view
    fun setRecycler() {
        // get all data
        recycler_view.adapter = Adapter(DataObject.getAllData())
        // set the layout manager aka update
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}