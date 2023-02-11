package com.example.bmlproject.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bmlproject.Models.Scripts

@Dao
interface MainDAO {

    @Insert(onConflict = REPLACE)
    fun insert(scripts: Scripts)

    @Query("SELECT * FROM scripts ORDER BY id DESC")
    fun getAll(): List<Scripts>

    @Query("UPDATE scripts SET type = :type, title = :title, script = :script, regularity = :regularity, date = :date WHERE id = :id")
    fun update(id: Int, type: String, title: String, script: String, regularity: String, date: String)

    @Delete
    fun delete(scripts: Scripts)
}