package fhku.sensorballgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        ArrayList<HighScoreEntry> rawEntrys = new ArrayList<HighScoreEntry>();

        ListView listView = (ListView) findViewById(R.id.hl);

        try {

            InputStream in = openFileInput("highscore");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = reader.readLine();

            while(line != null){
                String[] e = line.split(":");
                HighScoreEntry h = new HighScoreEntry();
                h.name = e[0];
                h.time = Long.parseLong(e[1]);
                rawEntrys.add(h);

                line = reader.readLine();

            }
            System.out.println("sort:");
            Collections.sort(rawEntrys);
            System.out.println("Make array");
            HighScoreEntry[] raw = rawEntrys.toArray(new HighScoreEntry[0]);
            System.out.println("output debug");
            System.out.println("Lines: "+raw.length+" al:"+rawEntrys.size());

            ArrayList<String> out = new ArrayList<String>();
            for(int i = 0;i<raw.length;i++){
                TimeDifference t = new TimeDifference(raw[i].time);
                out.add((i+1)+". "+raw[i].name+" - "+t.printMin()+":"+t.printSec()+","+t.printMil());
            }

            ArrayAdapter<String> listeAdapter =
                    new ArrayAdapter<>(
                            this, // Die aktuelle Umgebung (diese Activity)
                            R.layout.listelement_highscore, // ID der XML-Layout Datei
                            R.id.list_item_hightscore_textview, // ID des TextViews
                            out); // Beispieldaten in einer ArrayList


            listView.setAdapter(listeAdapter);










        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private class View {
    }
}
