package com.example.notesapp_assignment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "To_Do")
// model class for room db
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var priority:String,
    var date:String,
)