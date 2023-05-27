package com.example.dailyleveling.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val taskText: String,
    val status: Boolean,
    val createdAt: String,
    val priority: Int? = null,
    val notes: String? = null,
    val parentId: Long? = null
): Serializable
