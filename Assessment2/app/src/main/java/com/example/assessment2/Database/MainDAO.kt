package com.example.assessment2.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.assessment2.Models.Employees

@Dao
interface MainDAO {
    @Insert(onConflict = REPLACE)
    fun insert(employees: Employees)

    @Query("SELECT * FROM employees ORDER BY ID DESC")
    fun getAll(): List<Employees>

    @Query("UPDATE employees SET employee_id = :employee_id, employee_name = :employee_name, employee_designation = :employee_designation, employee_salary = :employee_salary WHERE ID = :id")
    fun update(id: Int, employee_id: String, employee_name: String, employee_designation: String, employee_salary: String)

    // delete using id
    @Query("DELETE FROM employees WHERE ID = :id")
    fun delete(id: Int)
}