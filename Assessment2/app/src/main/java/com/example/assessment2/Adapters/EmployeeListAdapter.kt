package com.example.assessment2.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment2.EditActivity
import com.example.assessment2.Models.Employees
import com.example.assessment2.R

class EmployeeListAdapter(private val context: Context, private var list: List<Employees>)
    : RecyclerView.Adapter<EmployeeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.employee_list, parent, false)

        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.id.text = list[position].getEmployeeID()
        holder.name.text = list[position].getEmployeeName()
        holder.designation.text = list[position].getEmployeeDesignation()
        holder.salary.text = list[position].getEmployeeSalary()

        holder.container.setOnClickListener {
            // go to edit activity with the data of the selected employee

            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra("id", list[position].getID())
            intent.putExtra("employeeId", list[position].getEmployeeID())
            intent.putExtra("name", list[position].getEmployeeName())
            intent.putExtra("designation", list[position].getEmployeeDesignation())
            intent.putExtra("salary", list[position].getEmployeeSalary())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public fun updateList(list: List<Employees>) {
        this.list = list
        notifyDataSetChanged()
    }
}


class EmployeeViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
    val container = itemView.findViewById<LinearLayout>(R.id.employee_container)
    val id = itemView.findViewById<TextView>(R.id.employeeId)
    val name = itemView.findViewById<TextView>(R.id.name)
    val designation = itemView.findViewById<TextView>(R.id.designation)
    val salary = itemView.findViewById<TextView>(R.id.salary)
}