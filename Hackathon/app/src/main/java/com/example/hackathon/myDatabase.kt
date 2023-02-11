package com.example.hackathon

import androidx.room.Database
import androidx.room.RoomDatabase

// get db with entities and version
@Database(entities = [ScriptEntity::class],version=1)
abstract class myDatabase : RoomDatabase() {
    // use DAO class which defines the methods
    abstract fun dao():DAO
}