package com.example.dailyleveling.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
    //---
    @Query("SELECT * FROM tasks_table ORDER BY id ASC")
    fun getAllTasksData(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE id = :taskId")
    fun getTaskById(taskId: Long): Task?

}
