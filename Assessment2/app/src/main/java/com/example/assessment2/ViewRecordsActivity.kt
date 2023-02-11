package com.example.assessment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.assessment2.Adapters.EmployeeListAdapter
import com.example.assessment2.Database.RoomDB
import com.example.assessment2.Models.Employees

class ViewRecordsActivity : AppCompatActivity() {

    lateinit var employees : MutableList<Employees>
    private lateinit var recyclerView : RecyclerView
    private lateinit var employeeListAdapter: EmployeeListAdapter
    private lateinit var database : RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_records)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.setHasFixedSize(true)

        database = RoomDB.getInstance(this)

        employees = database.mainDAO().getAll() as MutableList<Employees>

        employeeListAdapter = EmployeeListAdapter(this, employees)
        recyclerView.adapter = employeeListAdapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        val btn_home = findViewById<Button>(R.id.btn_home)

        btn_home.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}