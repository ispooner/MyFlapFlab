package com.illicitintelligence.myflapflab;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Background {

    Bitmap texture;

    private float positionX;

    private float speed = -8f;

    public Background(Resources res, float screenHeight) {
        texture = BitmapFactory.decodeResource(res, R.drawable.platformer_background_3);
        float scale = screenHeight / texture.getHeight();
        texture = Bitmap.createScaledBitmap(texture, (int)(texture.getWidth() * scale), (int)(texture.getHeight() * scale), false);

        positionX = 0f;
    }

    private void resetStartValues() {
        positionX = 0f;
    }

    public void onDraw(Canvas canvas, Paint paint) {
        positionX += speed;

        if(positionX < 0 - texture.getWidth()) {
            positionX += texture.getWidth();
        }

        canvas.drawBitmap(texture, positionX, 0, paint);
        canvas.drawBitmap(texture, positionX + texture.getWidth(), 0, paint);

    }
}
