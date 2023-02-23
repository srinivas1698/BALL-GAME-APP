package edu.sjsu.android.project2srinivasraochavan;

import android.content.Context;
import android.graphics.Bitmap;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    private final int BALL_SIZE = 150;
    private Bitmap court,ball;
    private float originX, originY, horizontalBound, verticalBound;
    private Particle particle;

    public MyView(Context context) {
        super(context);
        court= BitmapFactory.decodeResource(context.getResources(),R.drawable.court);
        ball= BitmapFactory.decodeResource(context.getResources(),R.drawable.ball);
        ball= Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, false);
        particle= new Particle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originX= w / 2f;
        originY= h / 2f;
        horizontalBound = originX - BALL_SIZE/ 2f;
        verticalBound = originY - BALL_SIZE/ 2f;

        court = Bitmap.createScaledBitmap(court, w, h,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(court,0,0,null);
        canvas.drawBitmap(ball,
                originX+particle.mPosX-BALL_SIZE/2f,
                originY-particle.mPosY-BALL_SIZE/2f,
                null);
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        int posx=canvas.getWidth()/2;
        int posy=canvas.getHeight()/2;
        canvas.drawText("Srinivas Chavan",posx,posy,paint);
        particle.updatePosition(MainActivity.x,MainActivity.y, MainActivity.timestamp);
        particle.resolveCollisionWithBounds(horizontalBound, verticalBound);
        invalidate();
    }
}

