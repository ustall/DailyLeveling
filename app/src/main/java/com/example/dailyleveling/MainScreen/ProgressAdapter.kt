package com.example.dailyleveling.MainScreen

import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView

class ProgressAdapter(private val progressBar: ProgressBar, private val textView: TextView) {

    fun setProgress(progress: Int) {
        if (progress in 0..100) {
            animateProgress(progress)
            textView.text = buildString {
            append(progress)
            append("%")
        }
        } else {
            throw IllegalArgumentException("Progress value must be between 0 and 100.")
        }
    }
    private fun animateProgress(progress: Int) {
        val currentProgress = progressBar.progress
        val animator = ObjectAnimator.ofInt(progressBar, "progress", currentProgress, progress)
        animator.apply {
            duration = ANIMATION_DURATION
            interpolator = ANIMATION_INTERPOLATOR
            target = progressBar
            start()
        }
    }
     companion object {
        private const val ANIMATION_DURATION = 400L
        private val ANIMATION_INTERPOLATOR = AccelerateDecelerateInterpolator()
    }
}
