package com.example.bmlproject.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bmlproject.Models.Scripts

@Database(entities = [Scripts::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    // create DAO instance
    abstract fun mainDAO(): MainDAO

    // create database using singleton pattern
    companion object {
        // set database name
        const val DATABASE_NAME = "BMLHackathonDB"
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