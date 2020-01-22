package com.illicitintelligence.myflapflab;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

public class Pipes {

    private Bitmap texture;
    private Bitmap rotated;
    final int pipeWidth = 100;
    final int blueoffset = 0;
    final int yellowoffset = 52;
    final int redoffset = 104;
    final int greenoffset = 156;

    float distanceApart = 500;

    private PointF position = new PointF();
    private PointF secondaryPosition = new PointF();
    private PointF velocity = new PointF();
    private Float separation = 400f;

    public Pipes(Resources res) {
        texture = BitmapFactory.decodeResource(res, R.drawable.colored_pipes);
        texture = Bitmap.createBitmap(texture, blueoffset,0,pipeWidth, texture.getHeight());
        texture = Bitmap.createScaledBitmap(texture, texture.getWidth() * 2, texture.getHeight() * 2, false);

        Matrix rot = new Matrix();
        rot.postRotate(180);

        rotated = Bitmap.createBitmap(texture, 0,0, texture.getWidth(), texture.getHeight(), rot, false);
        setStartValues();
    }

    private void setStartValues() {
        position.x = 1100f;
        position.y = 900f;

        secondaryPosition.x = position.x;
        secondaryPosition.y = position.y - rotated.getHeight() - separation;

        velocity.x = -12f;
        velocity.y = 0f;
    }

    public void onDraw(Canvas canvas, Paint paint) {
        position.x += velocity.x;
        secondaryPosition.x += velocity.x;

        canvas.drawBitmap(texture, position.x, position.y, paint);
        canvas.drawBitmap(rotated, secondaryPosition.x, secondaryPosition.y, paint);

        canvas.drawBitmap(texture, position.x + distanceApart, position.y, paint);
        canvas.drawBitmap(rotated, secondaryPosition.x + distanceApart, secondaryPosition.y, paint);

        canvas.drawBitmap(texture, position.x + distanceApart * 2, position.y, paint);
        canvas.drawBitmap(rotated, secondaryPosition.x + distanceApart * 2, secondaryPosition.y, paint);
    }
}








