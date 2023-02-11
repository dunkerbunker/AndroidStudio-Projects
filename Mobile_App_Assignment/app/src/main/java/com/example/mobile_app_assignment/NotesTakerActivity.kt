/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file the activity that is used to add or edit a note
    * it sends the data to the database and then returns to the main activity
*/

package com.example.mobile_app_assignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.mobile_app_assignment.Models.Notes
import io.github.muddz.styleabletoast.StyleableToast
import java.text.SimpleDateFormat
import java.util.*

class NotesTakerActivity : AppCompatActivity() {

    // variables that are used in multiple functions
    lateinit var notes: Notes
    var isOldNote : Boolean = false

    // run when the activity is created
    // it is a default function
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_taker)

        // initialise the variables with items that are in the layout
        var editText_title = findViewById<EditText>(R.id.editText_title)
        var editText_notes = findViewById<EditText>(R.id.editText_notes)
        var textView_title = findViewById<TextView>(R.id.textView_title)
        val imageView_save = findViewById<ImageView>(R.id.imageView_save)
        val imageView_back = findViewById<ImageView>(R.id.imageView_back)
        val button_save = findViewById<Button>(R.id.button_save)

        // create notes object
        notes = Notes()

        // check if the intent has any data under the name "old_note"
        // as when editing, data is sent under that name to this activity
        try {
            // get notes object from the intent
            notes = intent.getSerializableExtra("old_note") as Notes
            // set the text in the layout using data from intent
            editText_title.setText(notes.getTitle())
            editText_notes.setText(notes.getNotes())
            // set is old note to true so that the data is updated instead of added
            // this var will be used later
            isOldNote = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // change button text if it is an old note
        if (isOldNote) {
            button_save.text = "Update"
        }


        imageView_save.setOnClickListener{
            // get the text from the edit text
            var title : String = editText_title.text.toString()
            var description : String = editText_notes.text.toString()

            // description aka note cannot be empty
            // if it is, notify user and not proceed
            if (description.isEmpty()) {
                StyleableToast.makeText(this, "You can't leave notes as blank!", R.style.redToast).show()
                return@setOnClickListener
            }

            // *** if description is not empty below code will execute ***
            // EEE stands for day of the week
            // MMM stands for month
            // dd stands for day of the month
            // yyyy stands for year
            // hh:mm:ss stands for hour, minute, second
            var formatter : SimpleDateFormat = SimpleDateFormat("E, d MMM yyyy hh:mm:ss a")
            var date : Date = Date()

            // if it is not an old note, initialise a new notes object
            if (!isOldNote) {
                // create notes object
                notes = Notes()
            }

            // set the data in the notes object
            notes.setTitle(title)
            notes.setNotes(description)
            // format date before passing into notes object
            notes.setDate(formatter.format(date))

            // create intent
            val intent = Intent()
            // send back updated note
            intent.putExtra("notes", notes)
            // send back the activity status so that the main activity knows what to do
            // AKA if process was successful or not
            setResult(Activity.RESULT_OK, intent)
            // finish the activity and return to the main activity
            finish()
        }

        // the below onClickListener for the button is just another way for the user to save the data
        // the functions are identical to the above onClickListener
        button_save.setOnClickListener{
            var title : String = editText_title.text.toString()
            var description : String = editText_notes.text.toString()

            if (description.isEmpty()) {
                StyleableToast.makeText(this, "You can't leave notes as blank!", R.style.redToast).show()
                return@setOnClickListener
            }

            var formatter : SimpleDateFormat = SimpleDateFormat("E, d MMM yyyy hh:mm:ss a")
            var date : Date = Date()

            if (!isOldNote) {
                // create notes object
                notes = Notes()
            }

            notes.setTitle(title)
            notes.setNotes(description)
            // format date before passing into notes object
            notes.setDate(formatter.format(date))

            val intent = Intent()
            intent.putExtra("notes", notes)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        // if user clicks back button, finish the activity and return to the main activity
        imageView_back.setOnClickListener{
            finish()
        }

    }
}