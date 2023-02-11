package com.example.exam_crud.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.exam_crud.Models.Students

@Dao
interface MainDAO {
    @Insert(onConflict = REPLACE)
    fun insert(employees: Students)

    @Query("SELECT * FROM students ORDER BY ID DESC")
    fun getAll(): List<Students>

    // get one student
    @Query("SELECT * FROM students WHERE student_id = :student_id")
    fun getOne(student_id: String): List<Students>
}