package com.example.notesapp_assignment

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view.view.*
import java.util.*

// adapter class that will be used to get the view and bind it to the recycler view and display the data
class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // function to bind the data to the view
        var title = itemView.title
        var priority = itemView.priority
        var layout = itemView.mylayout
        var date = itemView.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        // inflating the view which means to get the whole view
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    // function to bind the data to the view
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        // setting background color according to the priority
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }

        // setting the title and priority and date in text
        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.date.text = data[position].date

        // setting on click listener to the view. when clicked, update the note
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }
    }

    // function to get the size of the list
    override fun getItemCount(): Int {
        return data.size
    }
}