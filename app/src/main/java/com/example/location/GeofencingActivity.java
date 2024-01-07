package com.example.location;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class GeofencingActivity extends AppCompatActivity {

    private GeofencingClient geofencingClient;
    private PendingIntent geofencePendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofencing);

        geofencingClient = LocationServices.getGeofencingClient(this);

        // Create a list of geofences to monitor
        List<Geofence> geofenceList = new ArrayList<>();
        // Populate geofenceList with Geofence objects

        // Create a geofence request
        GeofencingRequest geofencingRequest = new GeofencingRequest.Builder()
                .addGeofences(geofenceList)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build();

        // Create an intent for the geofence transition event
        Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
        geofencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Add geofences
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
                .addOnSuccessListener(aVoid -> {
                    // Geofences added successfully
                })
                .addOnFailureListener(e -> {
                    if (e instanceof ApiException) {
                        ApiException apiException = (ApiException) e;
                        int statusCode = apiException.getStatusCode();
                        if (statusCode == GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE) {
                            // Handle GEOFENCE_NOT_AVAILABLE error
                            showToast("Geofencing is not available on this device.");
                        } else if (statusCode == GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES) {
                            // Handle GEOFENCE_TOO_MANY_GEOFENCES error
                            showToast("Too many geofences are being monitored.");
                        } else if (statusCode == GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS) {
                            // Handle GEOFENCE_TOO_MANY_PENDING_INTENTS error
                            showToast("Too many geofence pending intents.");
                        }
                        else {
                            // Handle other errors
                            showToast("An error occurred: " + apiException.getMessage());
                        }
                    } else {
                        // Handle non-ApiException errors
                        showToast("An error occurred: " + e.getMessage());
                    }
                });


    }

    private void showToast(String s) {
    }
}
