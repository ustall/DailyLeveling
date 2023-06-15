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
    val updatedAt: String? = null,
    val priority: Int? = null,
    val type: String? = null,
    val dueDate: String? = null,
    val assignedTo: String? = null,
    val completedAt: String? = null,
    val notes: String? = null,
    val parentId: Long? = null
): Serializable
