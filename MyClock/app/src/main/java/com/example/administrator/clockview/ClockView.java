package com.example.administrator.clockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.util.zip.CRC32;

/**
 * Created by Administrator on 2017/8/25.
 */

public class ClockView extends View {

    //画笔
    private Paint hourPaint,minuPaint;
    //view的宽高
    private float mWidth,mHeight;
    private float circleRadius;
    //圆心坐标
    private float circleX,circleY;
    private float hour,minute;

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        //表盘、刻度、指针画笔
        hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setColor(Color.BLACK);
        hourPaint.setStyle(Paint.Style.STROKE);
        hourPaint.setStrokeCap(Paint.Cap.ROUND);
        hourPaint.setStrokeWidth(4);
        minuPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minuPaint.setColor(Color.BLACK);
        minuPaint.setStrokeCap(Paint.Cap.ROUND);
        minuPaint.setStrokeWidth(3);
    }

    public void setClock(float mHour,float mMinute){
        this.hour = mHour;
        this.minute = mMinute;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth  = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        circleRadius = (mWidth<mHeight)?(mWidth/2-9):(mHeight/2-9);
        circleX = mWidth / 2;
        circleY = mHeight /2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawDial(canvas);
        drawPointer(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(circleX,circleY,circleRadius,hourPaint);
    }


    /**
     * 钟表刻度
     * @param canvas
     */
    private void drawDial(Canvas canvas) {
        Point dialStartHour  = new Point(circleX, circleY-circleRadius);
        Point dialEndHour = new Point (circleX,circleY-circleRadius+8);
        Point dialStartMiu = new Point (circleX,circleY-circleRadius);
        Point dialEndMiu = new Point (circleX,circleY - circleRadius+2);
        for (int i=0;i<60;i++){
            if (i%5==0){
                canvas.drawLine(dialStartHour.getX(),dialStartHour.getY(),dialEndHour.getX(),dialEndHour.getY(),hourPaint);
            }else{
                canvas.drawLine(dialStartMiu.getX(),dialStartMiu.getY(),dialEndMiu.getX(),dialEndMiu.getY(),hourPaint);
            }
            canvas.rotate(6,circleX,circleY);
        }

    }
    private void drawPointer(Canvas canvas) {
        canvas.translate(circleX,circleY);
        float hourX = (float)Math.cos(Math.toRadians((hour+minute/60)*30-90))*circleRadius*0.5f;
        float hourY = (float)Math.sin(Math.toRadians((hour+minute/60)*30-90))*circleRadius*0.5f;
        float minuteX = (float)Math.cos(Math.toRadians(minute*6-90))*circleRadius*0.8f;
        float minuteY = (float)Math.sin(Math.toRadians(minute*6-90))*circleRadius*0.8f;
        canvas.drawLine(0,0,hourX,hourY,hourPaint);
        canvas.drawLine(0,0,minuteX,minuteY,minuPaint);
        canvas.drawLine(0,0,-hourX/3,-hourY/3,hourPaint);
        canvas.drawLine(0,0,-minuteX/3,-minuteY/3,minuPaint);
    }
}
