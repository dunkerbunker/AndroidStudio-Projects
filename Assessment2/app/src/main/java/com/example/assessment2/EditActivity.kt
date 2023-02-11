package com.example.assessment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.assessment2.Database.RoomDB

class EditActivity : AppCompatActivity() {

    private lateinit var database : RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val update_id = findViewById<EditText>(R.id.update_id)
        val update_name = findViewById<EditText>(R.id.update_name)
        val update_designation = findViewById<EditText>(R.id.update_designation)
        val update_salary = findViewById<EditText>(R.id.update_salary)

        val id = intent.getIntExtra("id", 0)
        val employeeId = intent.getStringExtra("employeeId")
        val name = intent.getStringExtra("name")
        val designation = intent.getStringExtra("designation")
        val salary = intent.getStringExtra("salary")

        update_id.setText(employeeId)
        update_name.setText(name)
        update_designation.setText(designation)
        update_salary.setText(salary)

        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_delete = findViewById<Button>(R.id.btn_delete)

        btn_update.setOnClickListener {
            val employeeId = update_id.text.toString()
            val name = update_name.text.toString()
            val designation = update_designation.text.toString()
            val salary = update_salary.text.toString()

            val database = RoomDB.getInstance(this)
            database.mainDAO().update(id, employeeId, name, designation, salary)

            // toast
            Toast.makeText(this, "Employee Updated", Toast.LENGTH_SHORT).show()

            // intent back
            val intent = Intent(this, ViewRecordsActivity::class.java)
            startActivity(intent)
        }

        btn_delete.setOnClickListener {
            val database = RoomDB.getInstance(this)
            database.mainDAO().delete(id)

            // toast
            Toast.makeText(this, "Employee Deleted", Toast.LENGTH_SHORT).show()

            // intent back
            val intent = Intent(this, ViewRecordsActivity::class.java)
            startActivity(intent)
        }
    }
}