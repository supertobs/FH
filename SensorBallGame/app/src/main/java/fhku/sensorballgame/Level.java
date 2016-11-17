package fhku.sensorballgame;

import android.graphics.Canvas;

/**
 * Created by tobibeck on 12.11.16.
 */

public abstract class Level {

    float pxFac;

    public abstract boolean isOnPath(int cx, int cy, int lx, int ly);
    public abstract boolean isInGoal(int cx, int cy, int lx, int ly);
    public abstract void  onDraw(Canvas canvas);

    public abstract void onMeasure(int width, int height);

    public float toPx(int dps){
        return dps * this.pxFac;
    }

    public void setPixelFac(float f){
        this.pxFac = f;
    }
}
