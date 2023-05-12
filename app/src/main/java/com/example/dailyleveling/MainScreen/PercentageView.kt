package com.example.dailyleveling.MainScreen

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.Float.min

class PercentageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var percentage: Float = 50f

    init {
        // Настройка параметров рисования
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Рисуем круг
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = min(centerX, centerY) - 20f
        canvas?.drawCircle(centerX, centerY, radius, paint)

        // Рисуем текст процента
        paint.color = Color.WHITE
        paint.textSize = 60f
        paint.textAlign = Paint.Align.CENTER
        val percentageText = "${percentage.toInt()}%"
        canvas?.drawText(percentageText, centerX, centerY + paint.textSize / 4, paint)
    }

    fun setPercentage(value: Float) {
        percentage = value
        invalidate() // Перерисовываем виджет
    }
}