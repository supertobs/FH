package fhku.sensorballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tobibeck on 12.11.16.
 */

public class LevelView extends View {

    private Level level;


    private int width;
    private int height;


    public LevelView(Context context) {
        super(context);
    }

    public LevelView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public LevelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public void draw(Canvas canvas) {
        if(this.level!=null){
            System.out.println("onDraw!");
            this.level.onDraw(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        if(this.level!=null){
            System.out.println("Measure");

            width = View.MeasureSpec.getSize(widthMeasureSpec);
            height = View.MeasureSpec.getSize(heightMeasureSpec);
            setMeasuredDimension(width, width);
            this.level.onMeasure(width,width);




        }
    }

    public void setLevel(Level l){
        this.level = l;
        this.level.setPixelFac(getResources().getDisplayMetrics().density);
        this.level.onMeasure(width,width);
        this.invalidate();


    }

    public Level getLevel(){
        return level;
    }


}
