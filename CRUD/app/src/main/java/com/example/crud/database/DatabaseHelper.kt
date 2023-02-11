/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is for the database and its functions used in the app
*/

package com.example.crud.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getStringOrNull
import com.example.crud.models.TaskListModel

// class initializing the database
class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    // Object that holds information of the DB
    companion object {
        private val DB_NAME = "task"
        private val DB_VERSION = 1
        private val TABLE_NAME = "tasklist"
        private val ID = "id"
        private val TASK_NAME = "taskname"
        private val TASK_DETAILS = "taskdetails"
    }

    // Creating the table
    override fun onCreate(db: SQLiteDatabase?) {
        // creating query
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $TASK_NAME TEXT, $TASK_DETAILS TEXT);"
        // executing query
        db?.execSQL(CREATE_TABLE)
    }

    // executes every time the database is updated
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // creating query
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        // executing query
        db?.execSQL(DROP_TABLE)
        // creating table again
        onCreate(db)
    }

    // function to get all the tasks in the database
    // return type will be a list of TaskListModel
    fun getAllTask(): List<TaskListModel> {
        // creating a list of TaskListModel
        val taskList = ArrayList<TaskListModel>()
        // creating / init a readable database
        val db = this.readableDatabase
        // query to get all the data from the table
        val query = "SELECT * FROM $TABLE_NAME"
        // executing the cursor
        // it will get value from the db one by one
        val cursor = db.rawQuery(query, null)

        // if the cursor has data
        // loop through all the cursor until it reaches the end
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // creating a new TaskListModel object
                val task = TaskListModel()
                // get data from the cursor
                task.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
                task.name = cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME))
                task.details = cursor.getString(cursor.getColumnIndexOrThrow(TASK_DETAILS))
                // add the object to the list
                taskList.add(task)
            } while (cursor.moveToNext())
        }
        // close the cursor
        cursor.close()
        // close DB
        db.close()
        // return the list
        return taskList
    }

    // insert operation
    // return type will be a boolean
    fun addTask(task: TaskListModel): Boolean {
        // creating / init a writable database
        val db = this.writableDatabase
        // creating a content value object
        val values = ContentValues()
        // putting data to the content value object
        values.put(TASK_NAME, task.name)
        values.put(TASK_DETAILS, task.details)
        // inserting the data to the table
        // 1 = success
        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        // -1 or 1 is returned depending on the success of the operation
        return (Integer.parseInt("$success") != -1)
    }

    // select data by id
    // gets task when id is passed
    // return type will be a TaskListModel
    fun getTask(id: Int): TaskListModel {
        // creating task object
        val task = TaskListModel()
        // creating / init a readable database
        val db = this.writableDatabase

        // query to get data by id
        val query = "SELECT * FROM $TABLE_NAME WHERE $ID = $id"
        // executing the cursor
        val cursor = db.rawQuery(query, null)

        // if the cursor has data get first
        cursor?.moveToFirst()

        // get data from the cursor
        if (cursor != null && cursor.moveToFirst()) {
            task.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
            task.name = cursor.getString(cursor.getColumnIndexOrThrow(TASK_NAME))
            task.details = cursor.getString(cursor.getColumnIndexOrThrow(TASK_DETAILS))
        }

        cursor.close()
        db.close()

        // return the task
        return task
    }

    // delete task by id
    // return type will be a boolean
    fun deleteTask(_id: Int): Boolean {
        val db = this.writableDatabase
        // query is run to delete the task
        // success is returned if the query is successful
        val success = db.delete(TABLE_NAME, "$ID=?", arrayOf(_id.toString())).toLong()
        db.close()
        // -1 or 1 is returned depending on the success of the operation
        return Integer.parseInt("$success") != -1
    }

    // update
    // return type will be a boolean
    fun updateTask(task: TaskListModel): Boolean {
        val db = this.writableDatabase
        // creating a content value object
        val values = ContentValues()
        // putting data to the content value object
        values.put(TASK_NAME, task.name)
        values.put(TASK_DETAILS, task.details)
        // query is run to update the task
        val success = db.update(TABLE_NAME, values, "$ID=?", arrayOf(task.id.toString())).toLong()
        db.close()
        // -1 or 1 is returned depending on the success of the operation
        return Integer.parseInt("$success") != -1
    }
}