package com.example.dailyleveling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.dailyleveling.AddTask.AddTaskActivity
import com.example.dailyleveling.MainScreen.ProgressAdapter
import com.example.dailyleveling.MainScreen.TaskAdapter
import com.example.dailyleveling.MainScreen.TaskItemVM
import com.example.dailyleveling.database.AppDatabase
import com.example.dailyleveling.database.Task
import com.example.dailyleveling.database.TaskRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton;

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var progressAdapter: ProgressAdapter
    private lateinit var taskItemVM: TaskItemVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        initialize()
        observeTasks()
        addTask()
    }

    private fun initialize() {
        initializeViewModel()
        initializeRecyclerView()
        initializeProgressAdapter()

    }
    private fun initializeViewModel() {
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
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
    } //RV A
    private fun initializeProgressAdapter() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val textView: TextView = findViewById(R.id.percentage_text)
        progressAdapter = ProgressAdapter(progressBar, textView)
    }
    private fun observeTasks() {
        taskItemVM.allTasks.observe(this, Observer { tasks ->
            tasks?.let { it ->
                tasks.takeIf { it.isNotEmpty() }?: listOf(insertExampleTask())
                val totalTasks = it.size
                val completedTasks = it.count { task -> task.status }
                val progress = calculateProgress(totalTasks, completedTasks)
                updateProgressAdapter(progress)
                taskAdapter.submitList(it)
            }
        })
    }
    private fun calculateProgress(totalTasks: Int, completedTasks: Int): Int {
        return if (totalTasks > 0) {
            (completedTasks.toFloat() / totalTasks * 100).toInt()
        } else {
            0
        }
    }
    private fun updateProgressAdapter(progress: Int) {
        progressAdapter.setProgress(progress)
    }
    private fun addTask() {
        val addButton = findViewById<FloatingActionButton>(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
    private fun insertExampleTask() {
        val task = Task(
            taskText = "Пример задачи",
            status = false,
            createdAt = "00"
        )
        repeat(10){taskItemVM.insertTask(task)}
    }//TaskSample
}