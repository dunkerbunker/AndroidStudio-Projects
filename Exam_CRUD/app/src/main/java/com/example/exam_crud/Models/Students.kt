package com.example.exam_crud.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "students")
public class Students : Serializable {
    @PrimaryKey(autoGenerate = true)
    private var ID: Int = 0

    @ColumnInfo(name = "student_id")
    private var studentID: String = ""

    @ColumnInfo(name = "student_name")
    private var studentName: String = ""

    @ColumnInfo(name = "grade1")
    private var grade1: Int = 0


    @ColumnInfo(name = "grade2")
    private var grade2: Int = 0


    @ColumnInfo(name = "grade3")
    private var grade3: Int = 0



    // getters and setters
    // as all fields are private
    fun getID(): Int {
        return ID
    }

    fun setID(ID: Int) {
        this.ID = ID
    }

    fun getStudentID(): String {
        return studentID
    }

    fun setStudentID(student_id: String) {
        this.studentID = student_id
    }

    fun getStudentName(): String {
        return studentName
    }

    fun setStudentName(student_name: String) {
        this.studentName = student_name
    }

    fun getGrade1(): Int {
        return grade1
    }

    fun setGrade1(grade1: Int) {
        this.grade1 = grade1
    }

    fun getGrade2(): Int {
        return grade2
    }

    fun setGrade2(grade2: Int) {
        this.grade2 = grade2
    }

    fun getGrade3(): Int {
        return grade3
    }

    fun setGrade3(grade3: Int) {
        this.grade3 = grade3
    }

}