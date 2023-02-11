package com.example.exam_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.exam_crud.Database.RoomDB
import com.example.exam_crud.Models.Students

class MainActivity : AppCompatActivity() {

    lateinit var student : Students
    lateinit var studentList: MutableList<Students>
    private lateinit var database : RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_id = findViewById<EditText>(R.id.et_id)
        val et_name = findViewById<EditText>(R.id.et_name)
        val et_grade1 = findViewById<EditText>(R.id.et_grade1)
        val tv_creditpoint1 = findViewById<TextView>(R.id.tv_creditpoint1)
        val et_value1 = findViewById<TextView>(R.id.tv_value1)

        val et_grade2 = findViewById<EditText>(R.id.et_grade2)
        val tv_creditpoint2 = findViewById<TextView>(R.id.tv_creditpoint2)
        val et_value2 = findViewById<TextView>(R.id.tv_value2)

        val et_grade3 = findViewById<EditText>(R.id.et_grade3)
        val tv_creditpoint3 = findViewById<TextView>(R.id.tv_creditpoint3)
        val et_value3 = findViewById<TextView>(R.id.tv_value3)

        val btn_add = findViewById<Button>(R.id.btn_add)
        val btn_calc = findViewById<Button>(R.id.btn_calc)
        val btn_view = findViewById<Button>(R.id.btn_view)

        student = Students()

        btn_add.setOnClickListener {
            if (et_id.text.toString().isEmpty() || et_name.text.toString().isEmpty() || et_grade1.text.toString().isEmpty() || et_grade2.text.toString().isEmpty() || et_grade3.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                student.setStudentID(et_id.text.toString())
                student.setStudentName(et_name.text.toString())
                // grade 1 needs to be pssed as int
                student.setGrade1(et_grade1.text.toString().toInt())
                student.setGrade2(et_grade2.text.toString().toInt())
                student.setGrade3(et_grade3.text.toString().toInt())

                database = RoomDB.getInstance(this)
                database.mainDAO().insert(student)

                Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show()

                et_id.text = null
                et_name.text = null
                et_grade1.text = null
                et_grade2.text = null
                et_grade3.text = null
            }
        }

        btn_view.setOnClickListener {
            database = RoomDB.getInstance(this)
            database.mainDAO().getOne(student.getStudentID())

            var std_id = student.getStudentID()

            // clear student object
            student = Students()

            // store value returned from getOne() method
            studentList = database.mainDAO().getOne(student.getStudentID()) as MutableList<Students>

            // set values to text fields
            et_id.setText(studentList[0].getStudentID())
            et_name.setText(studentList[0].getStudentName())
            et_grade1.setText(studentList[0].getGrade1().toString())
            et_grade2.setText(studentList[0].getGrade2().toString())
            et_grade3.setText(studentList[0].getGrade3().toString())

            Toast.makeText(this, "Student Retrieved", Toast.LENGTH_SHORT).show()
        }

        btn_calc.setOnClickListener {
            var temp = 0
            if (et_grade1.text.toString().toInt() < 50) {
                temp = 0
            } else if (et_grade1.text.toString().toInt() >= 50 && et_grade1.text.toString().toInt() <= 64.5) {
                temp = 1
            } else if (et_grade1.text.toString().toInt() >= 65 && et_grade1.text.toString().toInt() <= 74.5) {
                temp = 2
            } else if (et_grade1.text.toString().toInt() >= 75 && et_grade1.text.toString().toInt() <= 84.5) {
                temp = 3
            } else if (et_grade1.text.toString().toInt() >= 85 && et_grade1.text.toString().toInt() <= 100) {
                temp = 4
            }

            // value = temp * 4
            et_value1.text = (temp * 4).toString()

            if (temp == 0) {
                tv_creditpoint1.text = "FL"
            } else if (temp == 1) {
                tv_creditpoint1.text = "PS"
            } else if (temp == 2) {
                tv_creditpoint1.text = "CR"
            } else if (temp == 3) {
                tv_creditpoint1.text = "DI"
            } else if (temp == 4) {
                tv_creditpoint1.text = "HD"
            }


            // repeat for grade 2 and 3
            if (et_grade2.text.toString().toInt() < 50) {
                temp = 0
            } else if (et_grade2.text.toString().toInt() >= 50 && et_grade2.text.toString().toInt() <= 64.5) {
                temp = 1
            } else if (et_grade2.text.toString().toInt() >= 65 && et_grade2.text.toString().toInt() <= 74.5) {
                temp = 2
            } else if (et_grade2.text.toString().toInt() >= 75 && et_grade2.text.toString().toInt() <= 84.5) {
                temp = 3
            } else if (et_grade2.text.toString().toInt() >= 85 && et_grade2.text.toString().toInt() <= 100) {
                temp = 4
            }

            et_value2.text = (temp * 4).toString()

            if (temp == 0) {
                tv_creditpoint2.text = "FL"
            } else if (temp == 1) {
                tv_creditpoint2.text = "PS"
            } else if (temp == 2) {
                tv_creditpoint2.text = "CR"
            } else if (temp == 3) {
                tv_creditpoint2.text = "DI"
            } else if (temp == 4) {
                tv_creditpoint2.text = "HD"
            }

            if (et_grade3.text.toString().toInt() < 50) {
                temp = 0
            } else if (et_grade3.text.toString().toInt() >= 50 && et_grade3.text.toString().toInt() <= 64.5) {
                temp = 1
            } else if (et_grade3.text.toString().toInt() >= 65 && et_grade3.text.toString().toInt() <= 74.5) {
                temp = 2
            } else if (et_grade3.text.toString().toInt() >= 75 && et_grade3.text.toString().toInt() <= 84.5) {
                temp = 3
            } else if (et_grade3.text.toString().toInt() >= 85 && et_grade3.text.toString().toInt() <= 100) {
                temp = 4
            }

            et_value3.text = (temp * 4).toString()

            if (temp == 0) {
                tv_creditpoint3.text = "FL"
            } else if (temp == 1) {
                tv_creditpoint3.text = "PS"
            } else if (temp == 2) {
                tv_creditpoint3.text = "CR"
            } else if (temp == 3) {
                tv_creditpoint3.text = "DI"
            } else if (temp == 4) {
                tv_creditpoint3.text = "HD"
            }
        }



    }
}