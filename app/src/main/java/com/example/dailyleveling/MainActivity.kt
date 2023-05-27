package com.example.dailyleveling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.dailyleveling.MainScreen.TaskAdapter
import com.example.dailyleveling.MainScreen.TaskItemVM
import com.example.dailyleveling.database.AppDatabase
import com.example.dailyleveling.database.Task
import com.example.dailyleveling.database.TaskRepository

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskItemVM: TaskItemVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        initializeViewModel()
        initializeRecyclerView()

        observeTasks()
    }


    private fun initializeViewModel() {
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "task_database")
            .build()
        val taskDao = database.taskDao()
        val taskRepository = TaskRepository(taskDao)
        taskItemVM = TaskItemVM(taskRepository)
    } //VM

    private fun initializeRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.task_list_recycler_view)
        taskAdapter = TaskAdapter(taskItemVM)
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    } //RV

    private fun observeTasks() {
        taskItemVM.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                if (tasks.isEmpty())insertExampleTask()
                taskAdapter.submitList(it)
            }
        })
    }//TaskList Check

    private fun insertExampleTask() {
        val task = Task(
            taskText = "Пример задачи",
            status = false,
            createdAt = "00"
        )
        repeat(10){taskItemVM.insertTask(task)}
    }//TaskSample
}