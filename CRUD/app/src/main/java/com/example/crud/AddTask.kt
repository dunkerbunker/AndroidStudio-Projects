package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.crud.database.DatabaseHelper
import com.example.crud.models.TaskListModel

class AddTask : AppCompatActivity() {

    // create views objects
    lateinit var btn_save: Button
    lateinit var btn_del: Button
    lateinit var et_name: EditText
    lateinit var et_details: EditText
    // create database object
    var dbHandler: DatabaseHelper? = null
    // user to check if the user is updating or creating a new note
    var isEditMode : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        // initialize views
        btn_save = findViewById(R.id.btn_save)
        btn_del = findViewById(R.id.btn_del)
        et_name = findViewById(R.id.et_name)
        et_details = findViewById(R.id.et_details)

        // initialize database
        dbHandler = DatabaseHelper(this)

        // check if the user is updating or creating a new note
        // using extra data passed from the previous activity
        if (intent != null && intent.getStringExtra("Mode") == "E") {
            //edit mode
            isEditMode = true
            // change the button text
            btn_save.text = "Update Data"
            // show the delete button
            btn_del.visibility = View.VISIBLE

            // fetch task using the id passed from the previous activity
            val tasks : TaskListModel = dbHandler!!.getTask(intent.getIntExtra("Id", 0))
            // set the fetched data to the views
            et_name.setText(tasks.name)
            et_details.setText(tasks.details)

        } else {
            // insert new data
            isEditMode = false
            // change the button text
            btn_save.text = "Save Data"
            // remove the delete button
            btn_del.visibility = View.GONE
        }

        // click on save button
        btn_save.setOnClickListener {
            var success : Boolean = false
            val tasks : TaskListModel = TaskListModel()
            // check if in edit mode
            if (isEditMode) {
                //update data
                tasks.id = intent.getIntExtra("Id", 0)
                tasks.name = et_name.text.toString()
                tasks.details = et_details.text.toString()
                // store success
                success = dbHandler?.updateTask(tasks) as Boolean

            } else {
                //insert data
                tasks.name = et_name.text.toString()
                tasks.details = et_details.text.toString()
                // store success
                success = dbHandler?.addTask(tasks) as Boolean
            }

            // check if the data was saved successfully
            // if success is true, go back to the main activity
            if (success) {
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
                finish()
            } else {
                // else show toast message
                Toast.makeText(applicationContext, "Failed to save data", Toast.LENGTH_SHORT).show()
            }
        }

        // when delete button pressed
        btn_del.setOnClickListener {
            // create alert dialog
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Are you sure to delete this task?")
                .setPositiveButton("Yes") { dialog, i ->
                    // if user presses yes, delete the task
                    val success = dbHandler?.deleteTask(intent.getIntExtra("Id", 0)) as Boolean
                    if (success) {
                        finish()
                    }
                    dialog.dismiss()
                }
                // if user presses no, dismiss the dialog
                .setNegativeButton("No") { dialog, i ->
                    dialog.dismiss()
                }
            dialog.show()
        }
    }
}