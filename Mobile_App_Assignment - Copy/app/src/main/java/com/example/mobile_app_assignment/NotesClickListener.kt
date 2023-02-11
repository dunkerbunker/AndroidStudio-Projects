/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is the interface class to handle clicks on the recycler view item or card view
*/

package com.example.mobile_app_assignment

import androidx.cardview.widget.CardView
import com.example.mobile_app_assignment.Models.Notes

interface NotesClickListener {
    fun onClick(notes: Notes)
    fun onLongClick(notes: Notes, cardView: CardView)
}