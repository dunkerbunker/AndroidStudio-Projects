package com.example.bmlproject.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "scripts")
public class Scripts : Serializable {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    @ColumnInfo(name = "type")
    private var type: String = ""

    @ColumnInfo(name = "title")
    private var title: String = ""

    @ColumnInfo(name = "script")
    private var script: String = ""

    @ColumnInfo(name = "regularity")
    private var regularity: String = ""

    @ColumnInfo(name = "date")
    private var date: String = ""

    // Getters and Setters
    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getScript(): String {
        return script
    }

    fun setScript(script: String) {
        this.script = script
    }

    fun getRegularity(): String {
        return regularity
    }

    fun setRegularity(regularity: String) {
        this.regularity = regularity
    }

    fun getDate(): String {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }
}