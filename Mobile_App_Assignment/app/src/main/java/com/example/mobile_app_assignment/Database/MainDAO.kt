/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file is the DAO of the application and is responsible for all the database operations
    * AKA the data layer or queries
*/

package com.example.mobile_app_assignment.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.mobile_app_assignment.Models.Notes


// DAO stands for Data Access Object
@Dao
interface MainDAO {

    // Inserting data into the database
    @Insert(onConflict = REPLACE)
    fun insert(notes : Notes)

    // getAll is a function that returns a list of notes
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAll() : List<Notes>

    // update is a function that updates the notes
    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :id")
    fun update(id: Int, title: String, notes: String)

    // delete is a function that deletes the notes
    @Delete
    fun delete(notes: Notes)

    // pin or unpin the notes
    @Query("UPDATE notes SET pinned = :pin WHERE ID = :id")
    fun pin(id: Int, pin: Boolean)

}