package com.illicitintelligence.myflapflab

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameScreen(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint = Paint()

    private var running = false

    private var birb = Birb(this)

    private var pipe = Pipes(this.resources);

    init {
        isClickable = true

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        birb.onDraw(canvas, paint, resources)
        pipe.onDraw(canvas, paint)

        invalidate()
    }

    override fun callOnClick(): Boolean {
        //if(super.callOnClick()) return true

        birb.onClick()

        return true
    }

    override fun performClick(): Boolean {
        //return super.performClick()

        birb.onClick()
        return true
    }

}