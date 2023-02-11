package com.example.retrofitapihandling

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.InputStream

class MyAdapter (val context: Context, val userlist: List<MyDataItem>)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userId: TextView
        var title: TextView
        var img: ImageView

        init {
            userId = itemView.findViewById(R.id.userId)
            title = itemView.findViewById(R.id.title)
            img = itemView.findViewById(R.id.image_movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = View.inflate(context, R.layout.row_items, null)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userlist[position].id.toString()
        holder.title.text = userlist[position].title

        var imageUrl = "https://images.freeimages.com/images/large-previews/84f/canadian-flag-1444866.jpg"
        Glide.with(context).load(imageUrl).into(holder.img)

    }

    override fun getItemCount(): Int {
        return userlist.size
    }
}