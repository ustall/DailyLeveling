package com.example.dailyleveling.MainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyleveling.database.Task
import com.example.dailyleveling.database.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskItemVM(private val taskRepository: TaskRepository) : ViewModel() {

    val allTasks: LiveData<List<Task>>

    init {
        allTasks = taskRepository.allTasks
    }

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.insertTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.deleteTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.updateTask(task)
    }

}