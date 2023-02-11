package com.example.notesapp_assignment

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class CreateCard : AppCompatActivity() {

    // get db
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        // init db instance
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        // get the spinner / dropdown
        val create_priority = findViewById<Spinner>(R.id.create_priority)

        // set content of spinner and get chosen value
        val items = arrayOf("High", "Medium", "Low")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        // set custom spinner dropdown
        adapter.setDropDownViewResource(R.layout.spinner_list)
        create_priority.adapter = adapter

        // Calender
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // var to store date
        var date = "     Due - $day/${month+1}/$year"

        // show calender
        date_button.setOnClickListener {
            // get date picked and store
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                // Display Selected date in textbox
                date = "     Due - $mDay/${mMonth+1}/$mYear"
                date_button.setText(date)
            }, year, month, day)
            dpd.show()
        }

        // when save is clicked
        save_button.setOnClickListener {
            // check if fields empty
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_priority.selectedItem.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                // get values
                var title = create_title.getText().toString()
                var priority = create_priority.selectedItem.toString()
                var xdate = date
                // store in db
                DataObject.setData(title, priority, xdate)
                // call db method to store
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, xdate))
                }
                // go back home
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}