/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is an adapter class that allows incompatible classes to
    * interact with each other. In this case it allows the recycler view to
    * interact with the database.
*/

package com.example.mobile_app_assignment.Adapters

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_assignment.Models.Notes
import com.example.mobile_app_assignment.NotesClickListener
import com.example.mobile_app_assignment.R
import java.util.*

// NotesAdapter class takes in a list of notes, context and a listener
// extends recyclerview
class NotesListAdapter(private val context: Context, private var list: List<Notes>, private val listener: NotesClickListener)
    : RecyclerView.Adapter<NotesViewHolder>() {

    // onCreateViewHolder method creates a view holder via inflation
    // its a prebuilt method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = View.inflate(context, R.layout.notes_list, null)

        return NotesViewHolder(view)
    }

    // onBindViewHolder method binds the data to the view holder
    // its a prebuilt method
    // the API tag is used as getColor is only available in API 23 and above
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        // gets the current note info from the list
        // set title and date as selected
        holder.textView_title.text = list[position].getTitle()
        holder.textView_title.isSelected = true

        holder.textView_notes.text = list[position].getNotes()

        holder.textView_date.text = list[position].getDate()
        holder.textView_date.isSelected = true

        // if the note is pinned, add a save icon to the card view
        if (list[position].isPinned()) {
            holder.imageView_pin.setImageResource(R.drawable.ic_pin)
        } else {
            holder.imageView_pin.setImageResource(0)
        }

        // set random color to the cards
        val color_code = getRandomColor()
        // set the color to the card view
        holder.notes_container.setCardBackgroundColor(holder.itemView.resources.getColor(color_code, null))

        // set the listener to the card view
        holder.notes_container.setOnClickListener {
            listener.onClick(list[holder.adapterPosition])
        }

        // set long click listener to the card view
        holder.notes_container.setOnLongClickListener {
            listener.onLongClick(list[holder.adapterPosition], holder.notes_container)
            true
        }
    }

    // getItemCount method returns the size of the list
    // its a prebuilt method
    override fun getItemCount(): Int {
        return list.size
    }

    // filter method takes in a filtered list from search query
    // and updates the list
    public fun filterList(filteredList: List<Notes>) {
        list = filteredList
        notifyDataSetChanged()
    }

    // update method takes in a list of notes and updates the list
    // and notifies the adapter
    public fun updateList(list: List<Notes>) {
        this.list = list
        notifyDataSetChanged()
    }

    // get random color function
    private fun getRandomColor(): Int {
        // init colorCode list from colors.xml
        var colorCode = listOf<Int>(
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
            R.color.color7,
        )

        // get random color from colorCode list
        var randomColor : Int = Random().nextInt(colorCode.size)

        // return random color
        return colorCode.get(randomColor)
    }
}

// NotesViewHolder class that extends RecyclerView.ViewHolder
class NotesViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    val notes_container = itemView.findViewById<CardView>(R.id.notes_container)
    val textView_title = itemView.findViewById<TextView>(R.id.textView_title)
    val textView_notes = itemView.findViewById<TextView>(R.id.textView_notes)
    val textView_date = itemView.findViewById<TextView>(R.id.textView_date)
    val imageView_pin = itemView.findViewById<ImageView>(R.id.imageView_pin)

}