package com.fabioseixaslopes.hikerswatchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textViewInfoLatitude, textViewInfoLongitude,
            textViewInfoAltitude, textViewInfoAccuracy,
            textViewInfoAddress;
    LocationManager locationManager;
    LocationListener locationListener;
    Geocoder geocoder;

    @Override
    @SuppressLint("MissingPermission")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views setup
        textViewInfoLatitude = findViewById(R.id.textViewInfoLatitude);
        textViewInfoLongitude = findViewById(R.id.textViewInfoLongitude);
        textViewInfoAltitude = findViewById(R.id.textViewInfoAltitude);
        textViewInfoAccuracy = findViewById(R.id.textViewInfoAccuracy);
        textViewInfoAddress = findViewById(R.id.textViewInfoAddress);
        textViewInfoLatitude.setText(getText(R.string.loading));
        textViewInfoLongitude.setText(getText(R.string.loading));
        textViewInfoAltitude.setText(getText(R.string.loading));
        textViewInfoAccuracy.setText(getText(R.string.loading));
        textViewInfoAddress.setText(getText(R.string.loading));

        //Location/GPS
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            buildAlertMessageNoGps();
        }
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        locationListener = location -> {
            textViewInfoLatitude.setText(getString(R.string.latitude,location.getLatitude()));
            textViewInfoLongitude.setText(getString(R.string.longitude,location.getLongitude()));
            textViewInfoAltitude.setText(getString(R.string.altitude,location.getAltitude()));
            textViewInfoAccuracy.setText(getString(R.string.accuracy,location.getAccuracy()));

            try {
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                List<Address> listAddresses = geocoder.getFromLocation(userLocation.latitude, userLocation.longitude,1);
                if (!listAddresses.isEmpty()){
                    textViewInfoAddress.setText(getString(R.string.address,listAddresses.get(0).getAddressLine(0)));
                }
                else{
                    textViewInfoAddress.setText(getString(R.string.address_not_found));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        //ask for permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        //if already granted
        else{
            //request Location updates, using our listener. The both variables 0's is to get updates every X milliseconds or every X meters.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check if permission 0 (which in this case is GPS) was granted
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //request Location updates, using our listener. The both variables 0's is to get updates every X milliseconds or every X meters.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    //ask to enable GPS
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        final AlertDialog alert = builder.create();
        alert.show();
    }
}