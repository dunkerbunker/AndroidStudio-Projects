package com.example.hackathon

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class CreateScript : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_script)

        // init db instance
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "scripts_table"
        ).build()

        // get the spinner / dropdown
        val create_regularity = findViewById<Spinner>(R.id.create_regularity)

        val date_button = findViewById<Button>(R.id.date_button)
        val save_button = findViewById<Button>(R.id.save_button)
        val create_title = findViewById<EditText>(R.id.create_title)
        val create_script = findViewById<EditText>(R.id.create_script)
        val create_type = findViewById<EditText>(R.id.create_type)

        // set content of spinner and get chosen value
        val items = arrayOf("High", "Medium", "Low")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        // set custom spinner dropdown
        adapter.setDropDownViewResource(R.layout.spinner_list)
        create_regularity.adapter = adapter

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
                && create_regularity.selectedItem.toString().trim { it <= ' ' }.isNotEmpty()
                && create_script.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_type.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                // get values
                var title = create_title.getText().toString()
                var regularity = create_regularity.selectedItem.toString()
                var script = create_script.getText().toString()
                var type = create_type.getText().toString()
                var xdate = date
                // store in db
                DataObject.setData(type, title, script, regularity, xdate)
                // call db method to store
                GlobalScope.launch {
                    database.dao().insertScript(ScriptEntity(0, type, title, script, regularity, xdate))
                }
                // go back home
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}