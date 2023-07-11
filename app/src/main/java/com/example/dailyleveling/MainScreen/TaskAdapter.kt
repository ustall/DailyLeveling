package com.example.dailyleveling.MainScreen

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyleveling.R
import com.example.dailyleveling.database.Task

class TaskAdapter(private val taskItemVM: TaskItemVM) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTitleTextView: TextView = itemView.findViewById(R.id.task_title)
        private val checkIcon: ImageView = itemView.findViewById(R.id.check_icon)
        private val context = itemView.context
        private val uncheckedMarkColor = context.theme.obtainStyledAttributes(R.style.Theme_DailyLeveling, intArrayOf(R.attr.colorUncheckedMark)).getColor(0, 0)
        private val onSurfaceColor = context.theme.obtainStyledAttributes(R.style.Theme_DailyLeveling, intArrayOf(com.google.android.material.R.attr.colorOnSurface)).getColor(0, 0)
        private val primaryMarkColor = context.theme.obtainStyledAttributes(R.style.Theme_DailyLeveling, intArrayOf(com.google.android.material.R.attr.colorPrimary)).getColor(0, 0)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val task = getItem(position)
                    taskItemVM.updateTaskStatus(task.id, !task.status)
                    if (!task.status) {
                        val animator = ValueAnimator.ofArgb(primaryMarkColor,uncheckedMarkColor)
                        animator.duration = 1200
                        animator.addUpdateListener { valueAnimator ->
                            val color = valueAnimator.animatedValue as Int
                            checkIcon.setColorFilter(color)
                            taskTitleTextView.setTextColor(color)
                        }
                        animator.start()
                    }
                }
            }//короткое
            itemView.setOnLongClickListener {
                Toast.makeText(itemView.context, "Long Tap", Toast.LENGTH_SHORT).show()
                true
            }//длинное
        }
        fun bind(task: Task) {
            taskTitleTextView.text = task.taskText
            if (task.status) {
                // Задача выполнена
                checkIcon.setImageResource(R.drawable.baseline_checked_24)
                checkIcon.setColorFilter(uncheckedMarkColor)
                taskTitleTextView.setTextColor(uncheckedMarkColor)
                taskTitleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                // Задача не выполнена
                checkIcon.setImageResource(R.drawable.baseline_unchecked_24)
                taskTitleTextView.paintFlags = 0
                checkIcon.setColorFilter(onSurfaceColor)
                taskTitleTextView.setTextColor(onSurfaceColor)
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

