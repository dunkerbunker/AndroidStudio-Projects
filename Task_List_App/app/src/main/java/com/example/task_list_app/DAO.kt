package com.example.task_list_app

import androidx.room.*

@Dao
// all tasks that can be done on the database
// along with the input params
interface DAO {
    @Insert
    fun insertTask(entity: Entity)

    @Update
    fun updateTask(entity: Entity)

    @Delete
    fun deleteTask(entity: Entity)

    @Query("Select * from to_do")
    fun getTasks():List<CardInfo>

    @Query("Delete from to_do")
    fun deleteAll()
}