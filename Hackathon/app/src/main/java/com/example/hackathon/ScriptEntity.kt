package com.example.hackathon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scripts_table")
data class ScriptEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var type: String,
    var title: String,
    var script: String,
    var regularity: String,
    var date: String
}