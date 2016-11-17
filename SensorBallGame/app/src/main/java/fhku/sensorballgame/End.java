package fhku.sensorballgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class End extends AppCompatActivity implements View.OnClickListener{
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent i = getIntent();
        TextView t = (TextView) findViewById(R.id.time);

        time = i.getLongExtra("time",0);

        TimeDifference diff = new TimeDifference(time);

        t.setText(diff.printMin()+":"+diff.printSec()+","+diff.printMil());


    }



    public void onClick(View view){
        EditText n = (EditText) findViewById(R.id.editText);
        String name = n.getText().toString();

        if(name.length()==3) {
            //String time2 = ((EditText) findViewById(R.id.editText2)).getText().toString();
           File file = new File(getFilesDir(), "highscore");
            try {
                FileWriter w = new FileWriter(file,true);
                w.write(name+":"+time+"\n");
                w.close();
                Toast t = Toast.makeText(this,"Gespeichert",Toast.LENGTH_LONG);
                t.show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast t = Toast.makeText(this,"The Name needs to be 3 Letters long",Toast.LENGTH_LONG);
            t.show();
        }
    }
}
