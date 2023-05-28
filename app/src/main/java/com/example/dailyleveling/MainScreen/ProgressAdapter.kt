package com.example.dailyleveling.MainScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyleveling.R
import com.example.dailyleveling.database.Task

class ProgressAdapter(private val progressBar: ProgressBar, private val textView: TextView) {

    fun setProgress(progress: Int) {
        progressBar.progress = progress
        textView.text = "$progress%"
    }
}
