/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is the main activity of the app and will run when the app is launched
*/

package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.adapter.TaskListAdapter
import com.example.crud.database.DatabaseHelper
import com.example.crud.models.TaskListModel

class MainActivity : AppCompatActivity() {

    // create views objects
    lateinit var recycler_task: RecyclerView
    lateinit var btn_add: Button
    // create adapter
    var tasklistAdapter: TaskListAdapter? = null
    // create database
    var dbHandler: DatabaseHelper ?= null
    // create list for all tasks
    var tasklist : List<TaskListModel> = ArrayList<TaskListModel>()
    // create layout manager object
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get views
        recycler_task = findViewById(R.id.rv_list)
        btn_add = findViewById(R.id.bt_add_items)

        // inti database
        dbHandler = DatabaseHelper(this)
        // get all tasks
        fetchlist()

        // go to add task activity
        btn_add.setOnClickListener {
            val i = Intent(applicationContext, AddTask::class.java)
            startActivity(i)
        }
    }

    // method to fetch all tasks
    private fun fetchlist() {
        // get all tasks | function from dbHandler
        tasklist = dbHandler!!.getAllTask()
        // set adapter
        tasklistAdapter = TaskListAdapter(tasklist, applicationContext)
        // init layout manager
        linearLayoutManager = LinearLayoutManager(applicationContext)
        // set layout manager to recycler view
        recycler_task.layoutManager = linearLayoutManager
        recycler_task.adapter = tasklistAdapter
        // notify adapter
        tasklistAdapter?.notifyDataSetChanged()
    }
}