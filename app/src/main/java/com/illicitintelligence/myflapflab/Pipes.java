package com.illicitintelligence.myflapflab;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;

public class Pipes {

    private Bitmap texture;
    private Bitmap rotated;
    final int pipeWidth = 100;
    final int blueoffset = 0;
    final int yellowoffset = 140;
    final int redoffset = 200;
    final int greenoffset = 156;

    final float distanceApart = 700;
    final int pipeCount = 4;
    float[] pipeHeight = new float[pipeCount];

    Random rand = new Random();

    private PointF position = new PointF();
    private PointF secondaryPosition = new PointF();
    private PointF velocity = new PointF();
    private Float separation = 400f;

    public Boolean newPipe = true;

    public Pipes(Resources res) {
        texture = BitmapFactory.decodeResource(res, R.drawable.colored_pipes);
        texture = Bitmap.createBitmap(texture, yellowoffset,0,pipeWidth, texture.getHeight());
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

        for(int i = 0; i < pipeCount; i++) {
            pipeHeight[i] = rand.nextInt(900) + 400;
        }
    }

    private void generateNewHeight() {
        pipeHeight[pipeCount - 1] = rand.nextInt(900) + 400;
    }

    public void onDraw(Canvas canvas, Paint paint) {
        position.x += velocity.x;
        secondaryPosition.x += velocity.x;

        if(position.x < 0 - texture.getWidth()) {
            pipeHeight[0] = pipeHeight[1];
            pipeHeight[1] = pipeHeight[2];
            pipeHeight[2] = pipeHeight[3];
            generateNewHeight();
            position.x = position.x + distanceApart;
            secondaryPosition.x = secondaryPosition.x + distanceApart;
            newPipe = true;
        }

        for(int i = 0; i < pipeCount; i++) {
            canvas.drawBitmap(texture, position.x + distanceApart * i, pipeHeight[i], paint);
            canvas.drawBitmap(rotated, secondaryPosition.x + distanceApart * i, pipeHeight[i] - rotated.getHeight() - separation, paint);
        }
    }

    public RectF getCenterBox() {
        return new RectF(position.x, pipeHeight[0] - separation, position.x + texture.getWidth(), pipeHeight[0]);
    }
}








