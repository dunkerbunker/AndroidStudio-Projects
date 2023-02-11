package com.example.task_list_app

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
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.android.synthetic.main.activity_update_card.create_title
import kotlinx.android.synthetic.main.activity_update_card.date_button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class UpdateCard : AppCompatActivity() {
    // create db
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        // init db instance
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        // get spinner or dropdown
        val create_priority = findViewById<Spinner>(R.id.create_priority)

        // add content to spinner
        // and set the adapter / get chosen item
        val items = arrayOf("High", "Medium", "Low")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
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
            // get chosen date and store it
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                // Display Selected date in textbox
                date = "     Due - $mDay/${mMonth+1}/$mYear"
                date_button.setText(date)
            }, year, month, day)
            dpd.show()
        }

        // get data from intent to check if it is an update
        val pos = intent.getIntExtra("id", -1)

        // if it is an update
        if (pos != -1) {
            // get data from db
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            val date = DataObject.getData(pos).date

            // set data to views
            create_title.setText(title)
            create_priority.setSelection(adapter.getPosition(priority))
            date_button.setText(date)

            // delete button
            delete_button.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    // runs db delete method after passing in all the data into entity
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            create_title.text.toString(),
                            create_priority.selectedItem.toString(),
                            date
                        )
                    )
                }
                // go back to main activity
                intent()
            }

            update_button.setOnClickListener {
                // create new data object
                DataObject.updateData(
                    pos,
                    create_title.text.toString(),
                    create_priority.selectedItem.toString(),
                    date
                )
                GlobalScope.launch {
                    // runs db update method after passing in all the data into entity
                    database.dao().updateTask(
                        Entity(
                            pos + 1, create_title.text.toString(),
                            create_priority.selectedItem.toString(),
                            date
                        )
                    )
                }
                // go back to main activity
                intent()
            }

        }
    }

    fun intent() {
        // go back to main activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}