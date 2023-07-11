package com.example.dailyleveling.AddTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyleveling.MainActivity
import com.example.dailyleveling.MainScreen.TaskAdapter
import com.example.dailyleveling.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        supportActionBar?.hide()
        addTask()
    }
    private fun addTask() {
        val addButton = findViewById<CardView>(R.id.button_type)
        addButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}