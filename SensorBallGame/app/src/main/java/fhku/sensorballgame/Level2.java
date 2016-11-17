package fhku.sensorballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by tobibeck on 12.11.16.
 */

public class Level2 extends Level {
    private Paint paint = new Paint();
    private Rect rectangle;
    private int width;
    private int height;
    private float pxs;

    public int ballX;
    public int ballY;

    public Level2() {



    }


    @Override
    public void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        Paint goal = new Paint();
        goal.setStyle(Paint.Style.FILL);
        goal.setColor(Color.GREEN);


        //StartPoint
        canvas.drawCircle(this.toPx(45),this.toPx(45),this.toPx(30),paint);


        //EndPoint
        canvas.drawCircle(width-this.toPx(45),this.toPx(45),this.toPx(30),paint);



        paint.setStrokeWidth(this.toPx(10));
        canvas.drawLine(this.toPx(45),this.toPx(45),this.toPx(45),width-this.toPx(45),paint);
        canvas.drawLine(width-this.toPx(45),this.toPx(45),width-this.toPx(45),width-this.toPx(45),paint);
        canvas.drawLine(width-this.toPx(40),width-this.toPx(45),this.toPx(40),width-this.toPx(45),paint);

        //Goal
        canvas.drawCircle(width-this.toPx(45),this.toPx(45),this.toPx(15),goal);

        Paint p2 = new Paint();
        p2.setStyle(Paint.Style.STROKE);
        p2.setColor(Color.RED);

        //canvas.drawRect((float) 0,this.toPx(40), (width-this.toPx(40)),this.toPx(50) ,p2);
        //canvas.drawRect(width-this.toPx(50),this.toPx(40),width-this.toPx(40),width-this.toPx(40),p2);


    }

    public void onMeasure(int width, int height){
        this.width = width;
        this.height = height;



    }

    public boolean isOnPath(int cx,int cy,int lx,int ly){
       /* if((x>this.toPx(15)&& x<(width-this.toPx(20)))&&(y>this.toPx((15)))){

            if((x>this.toPx(75) && x<(width-this.toPx(50))) && (y<this.toPx(50) || y>this.toPx(70))){
                return false;
            }
            return true;
        }

        return false;*/

        //FIRST CIRCLE
        if((cx - this.toPx(45))*(cx - this.toPx(45)) + (cy - this.toPx(45))*(cy - this.toPx(45)) <= this.toPx(30)*this.toPx(30)){
            return true;
        }

        //Line Left to right
        if(cy<width-this.toPx(40) && cy > width-this.toPx(50) && cx < width-this.toPx(40)){
            return true;
        }

        //Line top to bottom - left

        if(cx>this.toPx(40) && cx < this.toPx(50) && cy > this.toPx(40) && cy < width-this.toPx(40)){
            return true;
        }

        //Line top to bottom - right

        if(cx<width-this.toPx(40) && cx > width-this.toPx(50) && cy > this.toPx(40) && cy < width-this.toPx(40)){
            return true;
        }

        //Finish Circle
        if((cx - (width-this.toPx(45)))*(cx - (width-this.toPx(45))) + (cy - (this.toPx(45)))*(cy - (this.toPx(45))) <= this.toPx(30)*this.toPx(30)){
            return true;
        }

        return false;
    }

    public boolean isInGoal(int cx,int cy,int lx,int ly){
        if((cx - (width-this.toPx(45)))*(cx - (width-this.toPx(45))) + (cy - (this.toPx(45)))*(cy - (this.toPx(45))) <= this.toPx(15)*this.toPx(15)){
            return true;
        }

        return false;
    }
}
