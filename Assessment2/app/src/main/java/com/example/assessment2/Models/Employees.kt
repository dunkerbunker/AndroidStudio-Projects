package com.example.assessment2.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "employees")
public class Employees : Serializable {
    @PrimaryKey(autoGenerate = true)
    private var ID: Int = 0

    @ColumnInfo(name = "employee_id")
    private var employeeID: String = ""

    @ColumnInfo(name = "employee_name")
    private var employeeName: String = ""

    @ColumnInfo(name = "employee_designation")
    private var employeeDesignation: String = ""

    @ColumnInfo(name = "employee_salary")
    private var employeeSalary: String = ""

    // getters and setters
    // as all fields are private
    fun getID(): Int {
        return ID
    }

    fun setID(ID: Int) {
        this.ID = ID
    }

    fun getEmployeeID(): String {
        return employeeID
    }

    fun setEmployeeID(employee_id: String) {
        this.employeeID = employee_id
    }

    fun getEmployeeName(): String {
        return employeeName
    }

    fun setEmployeeName(employee_name: String) {
        this.employeeName = employee_name
    }

    fun getEmployeeDesignation(): String {
        return employeeDesignation
    }

    fun setEmployeeDesignation(employee_designation: String) {
        this.employeeDesignation = employee_designation
    }

    fun getEmployeeSalary(): String {
        return employeeSalary
    }

    fun setEmployeeSalary(employee_salary: String) {
        this.employeeSalary = employee_salary
    }
}
