package com.example.tobibeck.maptrack;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TrackerActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager lm;

    protected FileWriter fw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationProvider lp = lm.getProvider(LocationManager.GPS_PROVIDER);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 2, this);

        File file = new File(getFilesDir(), "positions");
        try {
            fw = new FileWriter(file,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<LatLng> rawEntrys = new ArrayList<LatLng>();
        InputStream in = null;
        try {
            in = openFileInput("positions");

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                String[] e = line.split(":");

                LatLng l = new LatLng(Double.parseDouble(e[0]), Double.parseDouble(e[1]));

                rawEntrys.add(0, l);


                line = reader.readLine();

            }

            LatLng[] data = rawEntrys.toArray(new LatLng[0]);

            System.out.println("Positions: " + data.length);
            for (int i = 0; i < (data.length > 5 ? 5 : data.length); i++) {
                String out = (i+1)+". "+data[i].latitude+" "+data[i].longitude;
                switch(i){
                    case 0:
                        ((TextView) findViewById(R.id.pos1)).setText(out);
                        break;
                    case 1:
                        ((TextView) findViewById(R.id.pos2)).setText(out);
                        break;
                    case 2:
                        ((TextView) findViewById(R.id.pos3)).setText(out);
                        break;
                    case 3:
                        ((TextView) findViewById(R.id.pos4)).setText(out);
                        break;
                    case 4:
                        ((TextView) findViewById(R.id.pos5)).setText(out);
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast t = Toast.makeText(this,"Fetching location",Toast.LENGTH_LONG);
        t.show();
        try {
            fw.write(location.getLatitude()+":"+location.getLongitude()+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        System.out.println("provider enabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        System.out.println("Provider disabled");
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
