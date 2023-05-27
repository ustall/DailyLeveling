package com.example.dailyleveling.database

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasksData()

    fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    //my methods

    fun getAllNotes(): LiveData<List<Task>> {
        return taskDao.getAllTasksData()
    }

    fun getNoteById(id: Int): Task {
        return taskDao.getTaskById(id)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    //sub_main tasks
    fun getSubtasks(parentId: Long): List<Task> {
        return taskDao.getSubtasks(parentId)
    }

    fun getParentTask(parentId: Long): Task? {
        return taskDao.getParentTask(parentId)
    }
}
