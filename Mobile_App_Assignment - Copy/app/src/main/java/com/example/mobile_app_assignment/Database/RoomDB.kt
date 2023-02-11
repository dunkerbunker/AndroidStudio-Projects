/*
    * Created by Yoosuf Sayyid CYX27785 on 21/09/2022
    * This file is part of the project "Notes App" as part of the bachelors degree assignment
    * All rights reserved to Yoosuf Sayyid CYX27785
    *
    * This file creates the database and the tables using the Room library
    * The database is created using the singleton pattern
*/

package com.example.mobile_app_assignment.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_app_assignment.Models.Notes

// This calls the notes class to get the notes table
// also the version number is set to 1
// needs to be updated whenever the database is updated
@Database(entities = [Notes::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    // create DAO instance
    abstract fun mainDAO(): MainDAO

    // create database using singleton pattern
    companion object {
        // set database name
        const val DATABASE_NAME = "NoteApp"
        // set database instance
        private var database: RoomDB? = null

        // create database or return existing database
        @Synchronized
        fun getInstance(context: Context): RoomDB {
            // if no database exists, create one
            // allow main thread queries is used to allow the database to be accessed from the main thread
            // queries will be run on a separate thread
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