package com.illicitintelligence.myflapflab;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;

public class Birb {

    private Bitmap texture;
    private int frameIndex = 0;
    private int frameLength = 0;
    private int curLength = 0;

    private float gravity = 1f;
    private int scale = 10;

    private PointF position = new PointF();
    private PointF velocity = new PointF();

    TypedArray frames;

    public Birb(View parent) {
        frames = parent.getResources().obtainTypedArray(R.array.image_array);
        setStartValues(parent.getResources());
    }

    public void setStartValues(Resources res) {
        frameIndex = 0;
        position.x = 30f;
        position.y = 480f;
        velocity.x = 0f;
        velocity.y = -12f;
        texture = BitmapFactory.decodeResource(res, R.drawable.skeleton_01_fly_00);
        texture = Bitmap.createScaledBitmap(texture, texture.getWidth() / scale, texture.getHeight() / scale, false);
    }

    public RectF getBirdBounds() {
        return new RectF(position.x + 20, position.y + 20, texture.getWidth() + position.x - 10, position.y + texture.getHeight() - 20);
    }

    public void onClick() {
        velocity.y = -12f;
    }

    public void onDraw(Canvas canvas, Paint paint, Resources res) {
        texture = BitmapFactory.decodeResource(res, frames.getResourceId(frameIndex, R.drawable.skeleton_01_fly_00));
        texture = Bitmap.createScaledBitmap(texture, texture.getWidth() / scale, texture.getHeight() / scale, false);
        if(curLength >= frameLength) {
            frameIndex = ++frameIndex % frames.length();
            curLength = 0;
        }
        else {
            curLength++;
        }

        position.y += velocity.y;
        velocity.y += gravity;

        canvas.drawBitmap(texture, position.x, position.y, paint);
    }

}








