package com.example.exam_crud.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exam_crud.Models.Students

@Database(entities = [Students::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    // create DAO instance
    abstract fun mainDAO(): MainDAO

    companion object {
        // set database name
        const val DATABASE_NAME = "students"
        // set database instance
        private var database: RoomDB? = null

        @Synchronized
        fun getInstance(context: Context): RoomDB {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .build()
            }
            // return database
            return database!!
        }
    }
}