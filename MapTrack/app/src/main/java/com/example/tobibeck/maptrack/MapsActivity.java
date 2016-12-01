package com.example.tobibeck.maptrack;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<LatLng> rawEntrys = new ArrayList<LatLng>();
        InputStream in = null;
        try {
            in = openFileInput("positions");

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = reader.readLine();

            while(line != null){
                System.out.println(line);
                String[] e = line.split(":");

                LatLng l = new LatLng(Double.parseDouble(e[0]),Double.parseDouble(e[1]));

                rawEntrys.add(0,l);


                line = reader.readLine();

            }

        LatLng[] data = rawEntrys.toArray(new LatLng[0]);

            System.out.println("MArkers: "+data.length);
            for(int i = 0;i<(data.length>5?5:data.length);i++){
                System.out.println("added Marker");
                mMap.addMarker(new MarkerOptions().position(data[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(data[i]));
            }

            mMap.addPolyline(new PolylineOptions()
                    .add(data)
                    .width(5)
                    .color(Color.RED));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
