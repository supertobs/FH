package fhku.sensorballgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class BallGameActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    int ballSize = 0;
    int xMax = 0;
    int yMax = 0;

    LevelView gf;
    Level level;

    View ball;
    View gameboard;
    TextView timeView;

    TimeDifference diff;

    Stopwatch timer = new Stopwatch();

    int currentLevel = 0;

    long[] times;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ball_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ball = findViewById(R.id.circle);
        gameboard = findViewById(R.id.gameboard);

        gf = (LevelView) findViewById(R.id.bg);



        timeView = (TextView) findViewById(R.id.textView3);
        //setContentView(gf);

        /*Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        ballSize = (int) (size.x * 0.1);*/
        this.diff = new TimeDifference(0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.times = new long[2];
        //sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);

        this.startLevel(currentLevel);





    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    public void updateBall(float x, float y) {
        float xNew = ball.getX() - x;
        float yNew = ball.getY() + y;

        if(!level.isOnPath((int) (xNew+level.toPx(16)),(int) (yNew+level.toPx(16)),(int) xNew,(int) yNew)){
            xNew = level.toPx(45)-level.toPx(16);
            yNew = level.toPx(45)-level.toPx(16);
        }

        if (xNew < 0) {
            ball.setX(0);
        } else if (xNew > xMax) {
            ball.setX(xMax);
        } else {
            ball.setX(xNew);
        }

        if (yNew < 0) {
            ball.setY(0);
        } else if (yNew > yMax) {
            ball.setY(yMax);
        } else {
            ball.setY(yNew);
        }

        if(level.isInGoal((int) (xNew+level.toPx(16)),(int) (yNew+level.toPx(16)),(int) xNew,(int) yNew)){
            endLevel();
        }
        long time = timer.getElapsedTime();
        diff.setTime(time);


        timeView.setText(diff.printMin()+":"+diff.printSec()+","+diff.printMil());
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (yMax + xMax <= 0) {
            yMax = gameboard.getHeight() - ballSize;
            xMax = gameboard.getWidth() - ballSize;
        }

        updateBall(sensorEvent.values[0], sensorEvent.values[1]);


    }

    private void startLevel(int id){
        Level l = null;
        System.out.println("startlevel: "+id);

        switch(id){
            case 0:
                l = new Level1();
                break;
            case 1:
                l = new Level2();
                break;



        }

        this.level = l;
        this.gf.setLevel(this.level);


        timeView.setText("-- 3 --");
        final BallGameActivity that = this;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                that.timeView.setText("-- 2 --");
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            public void run() {
                that.timeView.setText("-- 1 --");
            }
        }, 2000);


        handler.postDelayed(new Runnable() {
            public void run() {

                timer.start();
                Sensor sensor = that.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                that.sensorManager.registerListener(that,sensor,SensorManager.SENSOR_DELAY_GAME);
            }
        }, 3000);
    }


    private void endLevel(){
        Toast t= Toast.makeText(this, "You are in goal!!", Toast.LENGTH_SHORT);
        t.show();
        timer.stop();
        this.times[currentLevel]=timer.getElapsedTime();
        timer = new Stopwatch();
        sensorManager.unregisterListener(this);

        final BallGameActivity that = this;
        currentLevel++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                if(that.currentLevel<2) {
                    that.updateBall((float) 10000, (float) 10000);
                    that.startLevel(that.currentLevel);
                } else {
                    long time = 0;
                    System.out.println("Times: "+times.length);
                    for(int i = 0;i<that.times.length;i++){
                        time = time+that.times[i];
                    }

                    Intent t = new Intent(that,End.class);
                    System.out.println(time);
                    t.putExtra("time",time);
                    startActivity(t);
                }
            }
        }, 1000);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
