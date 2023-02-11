/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is an adapter class that allows incompatible classes to
    * interact with each other. In this case it allows the recycler view to
    * interact with the database.
*/

package com.example.crud.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.AddTask
import com.example.crud.R
import com.example.crud.models.TaskListModel

// init the adapter class
// pass the context and the list of tasks
// the context is used to start the activity
// returns recycler view - task view holder
class TaskListAdapter (tasklist : List<TaskListModel>, internal var context: Context)
    : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    // create a list of tasks
    internal var tasklist : List<TaskListModel> = ArrayList()

    // bind the list of tasks to the adapter
    init {
        this.tasklist = tasklist
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // describes an item view about its place within the RecyclerView
        // maps the data to the view holder
        var name : TextView = view.findViewById(R.id.txt_name)
        var details : TextView = view.findViewById(R.id.txt_details)
        var btn_edit : TextView = view.findViewById(R.id.btn_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        // instantiate the xml layout into object
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_task_list, parent, false)
        // calling the view holder class
        return TaskViewHolder(view)
    }

    // binds the data to the view holder
    // takes the position of the item in the list
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        // get the task at the position
        val tasks = tasklist[position]
        // set the text of the text views
        holder.name.text = tasks.name
        holder.details.text = tasks.details

        // set the edit button to start the add task activity
        holder.btn_edit.setOnClickListener {
            //edit task
            val i = Intent(context, AddTask::class.java)
            // AddTask view will change depending on edit or creating
            // pass mode and task id as extras
            i.putExtra("Mode","E")
            i.putExtra("Id",tasks.id)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
    }

    // return the size of the list
    override fun getItemCount(): Int {
        return tasklist.size
    }
}