package com.illicitintelligence.myflapflab

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class GameScreen(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint = Paint()

    private var running = true

    private var birb = Birb(this)

    private var pipe = Pipes(this.resources)

    private lateinit var background : Background

    private var score = 0

    private var start = true

    init {
        isClickable = true

    }

    private fun reset() {
        birb = Birb(this)
        pipe = Pipes(this.resources)
        background
        running = true
        score = 0
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(start) {
            background = Background(resources, height.toFloat())
            paint.setColor(Color.BLACK)
            //canvas.drawARGB(255, 0,0,255)
            background.onDraw(canvas, paint)
            paint.textSize = 128f
            canvas.drawText("Tap to play", width / 2f - 400, height / 2f, paint)

            return
        }

        if(checkIntersect(birb.birdBounds, pipe.centerBox)) {


            paint.setColor(Color.argb(255, 0,0,0))
            background.onDraw(canvas, paint)

            birb.onDraw(canvas, paint, resources)
            pipe.onDraw(canvas, paint)

            paint.setColor(Color.argb(100, 255, 0,0))
            //canvas.drawRect(birb.birdBounds, paint)
            //canvas.drawRect(pipe.centerBox, paint)

            paint.setColor(Color.BLACK)
            paint.textSize = 128f
            increaseScore(birb.birdBounds, pipe.centerBox)


            canvas.drawText(score.toString(), 20f, 128f, paint)

            invalidate()
        }
        else {
            background.onDraw(canvas, paint);
            canvas.drawARGB(100, 255, 0, 0)
            paint.textSize = 128f;
            canvas.drawText("Score: " + score, width / 2f - 400, height / 2f, paint)
            canvas.drawText("Tap to play", width / 2f - 400, height / 2f + 128, paint)
            running = false
        }
    }

    override fun performClick(): Boolean {
        //return super.performClick()
        if(start) {
            start = false
            invalidate()
            return true
        }
        if(running) {
            birb.onClick()
        }
        else {
            running = true
            reset()
            invalidate()
        }
        return true
    }

    /*
        returns true if the bird is before the box, after the box.
        returns true if the bird is in the box.
        returns false if the bird is between left right, but above or below the box.
     */
    private fun checkIntersect(bird: RectF, center: RectF) : Boolean {
        if(bird.right < center.left || bird.left > center.right) {
            return true
        }
        if(bird.right > center.left && bird.left < center.right) {
            if(bird.top > center.top && bird.bottom < center.bottom) {
                return true
            }
        }

        return false
    }



    private fun increaseScore(bird: RectF, center: RectF) {
        if(bird.centerX() > center.centerX() && pipe.newPipe) {
            score += 1
            pipe.newPipe = false
        }
    }

}