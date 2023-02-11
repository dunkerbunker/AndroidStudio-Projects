package com.example.bmlproject.Adapters

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmlproject.Models.Scripts
import com.example.bmlproject.R
import com.example.bmlproject.ScriptsClickListener
import java.util.*

// NotesAdapter class takes in a list of notes, context and a listener
// extends recyclerview
class ScriptsAdapter(private val context: Context, private var list: List<Scripts>, private val listener: ScriptsClickListener)
    : RecyclerView.Adapter<ScriptsViewHolder>() {

    // onCreateViewHolder method creates a view holder via inflation
    // its a prebuilt method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScriptsViewHolder {
        val view = View.inflate(context, R.layout.scripts, null)

        return ScriptsViewHolder(view)
    }

    // onBindViewHolder method binds the data to the view holder
    // its a prebuilt method
    // the API tag is used as getColor is only available in API 23 and above
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ScriptsViewHolder, position: Int) {

        // gets the current note info from the list
        // set title and date as selected
        holder.textView_title.text = list[position].getTitle()
        holder.textView_title.isSelected = true

        holder.textView_script.text = list[position].getScript()
        holder.textView_script.isSelected = true

        holder.textView_regularity.text = list[position].getRegularity()
        holder.textView_regularity.isSelected = true

        val regularity = list[position].getRegularity()

        holder.textView_date.text = list[position].getDate()
        holder.textView_date.isSelected = true

        holder.textView_type.text = list[position].getType()
        holder.textView_type.isSelected = true

        when (regularity) {
            "Frequent" -> holder.scripts_container.setBackgroundColor(context.getColor(R.color.bml_red))
            "Regular" -> holder.scripts_container.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.scripts_container.setBackgroundColor(Color.parseColor("#A9D18E"))
        }

        // set the listener to the card view
        holder.scripts_container.setOnClickListener {
            listener.onClick(list[holder.adapterPosition])
        }
    }

    // getItemCount method returns the size of the list
    // its a prebuilt method
    override fun getItemCount(): Int {
        return list.size
    }

    // filter method takes in a filtered list from search query
    // and updates the list
    public fun filterList(filteredList: List<Scripts>) {
        list = filteredList
        notifyDataSetChanged()
    }

    // update method takes in a list of notes and updates the list
    // and notifies the adapter
    public fun updateList(list: List<Scripts>) {
        this.list = list
        notifyDataSetChanged()
    }

}

// NotesViewHolder class that extends RecyclerView.ViewHolder
class ScriptsViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

    val scripts_container = itemView.findViewById<LinearLayout>(R.id.scripts_container)
    val textView_title = itemView.findViewById<TextView>(R.id.title)
    val textView_script = itemView.findViewById<TextView>(R.id.script)
    val textView_regularity = itemView.findViewById<TextView>(R.id.regularity)
    val textView_date = itemView.findViewById<TextView>(R.id.date)
    val textView_type = itemView.findViewById<TextView>(R.id.type)

}