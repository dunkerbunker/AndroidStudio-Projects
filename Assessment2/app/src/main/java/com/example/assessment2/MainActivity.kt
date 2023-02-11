package com.example.assessment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment2.Adapters.EmployeeListAdapter
import com.example.assessment2.Database.RoomDB
import com.example.assessment2.Models.Employees

class MainActivity : AppCompatActivity() {

    lateinit var employee: Employees
    private lateinit var database : RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var id = findViewById<TextView>(R.id.et_id)
        var name = findViewById<TextView>(R.id.et_name)
        var designation = findViewById<TextView>(R.id.et_designation)
        var salary = findViewById<TextView>(R.id.et_salary)
        var btn_submit = findViewById<TextView>(R.id.btn_submit)
        var btn_view = findViewById<TextView>(R.id.btn_view)

        employee = Employees()

        btn_submit.setOnClickListener {

            // check if any of the fields are empty
            // if empty then toast
            if (id.text.toString().isEmpty() || name.text.toString().isEmpty() || designation.text.toString().isEmpty() || salary.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                // if not empty then save the data
                employee.setEmployeeID(id.text.toString())
                employee.setEmployeeName(name.text.toString())
                employee.setEmployeeDesignation(designation.text.toString())
                employee.setEmployeeSalary(salary.text.toString())

                database = RoomDB.getInstance(this)
                database.mainDAO().insert(employee)

                Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show()

                // clear the fields
                id.text = null
                name.text = null
                designation.text = null
                salary.text = null
            }
        }

        btn_view.setOnClickListener {
            val intent = Intent(this, ViewRecordsActivity::class.java)
            startActivity(intent)
        }
    }
}