/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is the model class for the notes
*/

package com.example.mobile_app_assignment.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// declaring the table name in RoomDB
@Entity(tableName = "notes")
public class Notes : Serializable {

    // declaring the primary key
    @PrimaryKey(autoGenerate = true)
    private var ID: Int = 0

    // setting other columns
    @ColumnInfo(name = "title")
    private var title: String = ""

    @ColumnInfo(name = "notes")
    private var notes : String = ""

    @ColumnInfo(name = "date")
    private var date : String = ""

    @ColumnInfo(name = "pinned")
    private var pinned : Boolean = false

    // getters and setters
    // as all fields are private
    fun getID(): Int {
        return ID
    }

    fun setID(ID: Int) {
        this.ID = ID
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getNotes(): String {
        return notes
    }

    fun setNotes(notes: String) {
        this.notes = notes
    }

    fun getDate(): String {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun isPinned(): Boolean {
        return pinned
    }

    fun setPinned(pinned: Boolean) {
        this.pinned = pinned
    }

}
