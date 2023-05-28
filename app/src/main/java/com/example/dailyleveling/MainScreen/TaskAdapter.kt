package com.example.dailyleveling.MainScreen

import android.content.Context
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyleveling.R
import com.example.dailyleveling.database.Task

class TaskAdapter(private val taskItemVM: TaskItemVM) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView, taskItemVM)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    inner class TaskViewHolder(itemView: View, private val taskItemVM: TaskItemVM) : RecyclerView.ViewHolder(itemView) {
        private val taskTitleTextView: TextView = itemView.findViewById(R.id.task_title)
        private val checkIcon: ImageView = itemView.findViewById(R.id.check_icon)
        init {
            checkIcon.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val task = getItem(position)
                    if (task.status) {
                        taskItemVM.updateTaskStatus(task.id, false)
                    } else {
                        taskItemVM.updateTaskStatus(task.id, true)
                    }
                }
            }
        }

        fun bind(task: Task) {
            taskTitleTextView.text = task.taskText
            if (task.status) {
                // Задача выполнена
                checkIcon.setImageResource(R.drawable.baseline_checked_24)
                checkIcon.setColorFilter(ContextCompat.getColor(itemView.context, android.R.color.darker_gray))
                taskTitleTextView.paintFlags = taskTitleTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                // Задача не выполнена
                checkIcon.setImageResource(R.drawable.baseline_unchecked_24)
                taskTitleTextView.paintFlags = taskTitleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                checkIcon.setColorFilter(ContextCompat.getColor(itemView.context, R.color.purple_200))
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}
