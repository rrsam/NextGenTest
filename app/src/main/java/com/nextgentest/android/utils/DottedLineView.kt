package com.nextgentest.android.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.nextgentest.android.R

class DottedLineView constructor(context: Context,attrs: AttributeSet) : View(context, attrs) {

    private val horPaint = Paint()
    private val vertPaint = Paint()
    private val horDashWidth = context.resources.getDimensionPixelSize(R.dimen.dp6).toFloat()
    private val horDashGap = context.resources.getDimensionPixelSize(R.dimen.dp2).toFloat()
    private val vertDashWidth = context.resources.getDimensionPixelSize(R.dimen.dp15).toFloat()
    private val vertDashGap = context.resources.getDimensionPixelSize(R.dimen.dp8).toFloat()
    private val lineWidth = context.resources.getDimensionPixelSize(R.dimen.dp2).toFloat()


    init {
        horPaint.isAntiAlias = true
        horPaint.color = Color.BLACK
        horPaint.style = Paint.Style.STROKE
        horPaint.strokeWidth = lineWidth
        horPaint.pathEffect = DashPathEffect(floatArrayOf(horDashWidth,horDashGap),0f)

        vertPaint.isAntiAlias = true
        vertPaint.color = Color.BLACK
        vertPaint.style = Paint.Style.STROKE
        vertPaint.strokeWidth = lineWidth
        vertPaint.pathEffect = DashPathEffect(floatArrayOf(vertDashWidth,vertDashGap),0f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawLine(0f,0f,width.toFloat(),0f,horPaint)
            drawLine(0f,height.toFloat(),width.toFloat(),height.toFloat(),horPaint)

            drawLine(0f,vertDashGap,0f,height.toFloat() - vertDashGap,vertPaint)
            drawLine(width.toFloat(),vertDashGap,width.toFloat(),height.toFloat() - vertDashGap,vertPaint)

        }
    }
}

