package com.example.tobibeck.maptrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);
    }

    public void onTracker(View view) {
        Intent i = new Intent(this,TrackerActivity.class);
        startActivity(i);

    }

    public void onReset(View view) {
        File file = new File(getFilesDir(), "position");
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
