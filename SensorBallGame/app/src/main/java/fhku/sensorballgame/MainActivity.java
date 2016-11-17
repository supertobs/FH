package fhku.sensorballgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button ballGame;
    Button sensorLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ballGame = (Button) findViewById(R.id.button_ball_game);
        sensorLog = (Button) findViewById(R.id.button_sensor_log);
    }

    public void start(View view) {
        if (ballGame.equals(view)) {
            Intent t = new Intent(this, BallGameActivity.class);
            t.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
           // t.putExtra("time",(long)12345);
            startActivity(t);
        } else if (sensorLog.equals(view)) {

            Intent t = new Intent(this, HighScore.class);
            startActivity(t);

        } else{
            File file = new File(getFilesDir(), "highscore");
            try {
                FileWriter w = new FileWriter(file,false);

                w.write("");
                w.close();
                Toast t = Toast.makeText(this,"Zurückgesetzt",Toast.LENGTH_LONG);
                t.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
