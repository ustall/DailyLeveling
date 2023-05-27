package com.example.dailyleveling.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks_table")
    fun getAllTasks(): List<Task>

    @Insert
    fun insertTask(task: Task)

    //my methods

    @Query("SELECT * FROM tasks_table ORDER BY id ASC")
    fun getAllTasksData(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE id = :id")
    fun getTaskById(id: Int): Task

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    //sub_main tasks
    @Query("SELECT * FROM tasks_table WHERE parentId = :parentId")
    fun getSubtasks(parentId: Long): List<Task>

    @Query("SELECT * FROM tasks_table WHERE id = :parentId")
    fun getParentTask(parentId: Long): Task?

}
