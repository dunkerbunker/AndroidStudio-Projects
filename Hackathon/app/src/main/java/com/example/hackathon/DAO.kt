package com.example.hackathon

import androidx.room.*

@Dao
interface DAO {
    @Insert
    fun insertScript(ScriptEntity: ScriptEntity)

    @Update
    fun updateScript(ScriptEntity: ScriptEntity)

    @Delete
    fun deleteScript(ScriptEntity: ScriptEntity)

    @Query("SELECT * FROM scripts_table")
    fun getScripts(): List<ScriptModel>

    @Query("Delete FROM scripts_table")
    fun deleteAll()
}