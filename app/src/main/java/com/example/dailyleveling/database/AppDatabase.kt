package com.example.dailyleveling.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
