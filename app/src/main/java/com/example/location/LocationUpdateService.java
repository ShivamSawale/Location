package com.example.location;

//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.app.Service;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.location.LocationRequest;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.provider.Settings;
//import android.util.Log;
//
//public class LocationUpdateService extends Service {
//
//    private LocationManager locationManager;
//    private static final String TAG = "LocationUpdateService";
//
//    private final LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            Log.d(TAG, "Location Updated: " + location.getLatitude() + ", " + location.getLongitude());
//            // Handle the updated location here
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    };
//
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER,
//                1000,   // Minimum time interval between location updates (in milliseconds)
//                0,      // Minimum distance between location updates (in meters)
//                locationListener
//        );
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        locationManager.removeUpdates(locationListener);
//    }
//}

// The above code is old and original



//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.NotificationCompat;
//import androidx.core.content.ContextCompat;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//
//public class LocationUpdateService extends Service {
//
//    private LocationManager locationManager;
//    private static final String TAG = "LocationUpdateService";
//    private static final int LOCATION_NOTIFICATION_ID = 123;
//    private static final String CHANNEL_ID = "LocationUpdateChannel";
//
//    private final LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            Log.d(TAG, "Location Updated: " + location.getLatitude() + ", " + location.getLongitude());
//            // Handle the updated location here
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//            // Provider (e.g., GPS) has been enabled
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            // Provider (e.g., GPS) has been disabled
//        }
//    };
//
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            // Check if GPS_PROVIDER is available before requesting updates
//            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                locationManager.requestLocationUpdates(
//                        LocationManager.GPS_PROVIDER,
//                        1000,   // Minimum time interval between location updates (in milliseconds)
//                        0,      // Minimum distance between location updates (in meters)
//                        locationListener
//                );
//            } else {
//                // GPS provider is not available
//                // Handle accordingly (e.g., show a message to the user)
//                showEnableLocationMessage();
//            }
//        }
//
//        // Start the service in the foreground with a notification
//        startForeground(LOCATION_NOTIFICATION_ID, createNotification());
//    }
//
//    private void showEnableLocationMessage() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Location Services Disabled")
//                .setMessage("Please enable location services to use this feature.")
//                .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Open the device settings to enable location services
//                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        startActivity(intent);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // User chose to cancel, handle as needed
//                        dialog.dismiss();
//                    }
//                })
//                .show();
//    }
//
//
//    private Notification createNotification() {
//        // Create a notification for the foreground service
//        // Customize the notification based on your requirements
//
//        // Create a notification channel for Android Oreo and higher
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    CHANNEL_ID,
//                    "Location Update Channel",
//                    NotificationManager.IMPORTANCE_DEFAULT
//            );
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(channel);
//            }
//        }
//
//        // Create the intent that will open when the user taps the notification
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(
//                this,
//                0,
//                notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.baseline_notifications_24)
//                .setContentTitle("Location Update Service")
//                .setContentText("Service is actively updating location")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent);
//
//        return builder.build();
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // Remove location updates when the service is destroyed
//        locationManager.removeUpdates(locationListener);
//    }
//}











//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//
//public class LocationUpdateService extends AppCompatActivity {
//
//    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
//    private FusedLocationProviderClient fusedLocationClient;
//    private LocationCallback locationCallback;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//
//        // Check and request location permissions if needed
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    LOCATION_PERMISSION_REQUEST_CODE);
//        } else {
//            initLocationUpdates();
//        }
//    }
//
//    private void initLocationUpdates() {
//        // Set up location request
//        LocationRequest locationRequest = new LocationRequest();
//        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        locationRequest.setInterval(50000); // 5 seconds
//
//        // Set up location callback
//        locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                if (locationResult != null) {
//
//                    Location location = locationResult.getLastLocation();
//                    // Handle the new location update
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    // Update UI, send to server, etc.
//                }
//            }
//        };
//
//        // Request location updates
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, initialize location updates
//                initLocationUpdates();
//            } else {
//                // Permission denied, handle accordingly (e.g., show a message)
//                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Stop location updates when the activity is destroyed
//        if (fusedLocationClient != null && locationCallback != null) {
//            fusedLocationClient.removeLocationUpdates(locationCallback);
//        }
//    }
//}
//
//






import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class LocationUpdateService extends Service {

    private static final String TAG = "LocationUpdateService";
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Setting up the  location request with a shorter interval and distance
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000); // 1 second
        locationRequest.setFastestInterval(500); // 0.5 seconds
        locationRequest.setSmallestDisplacement(5f); // 5 meters

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    Log.d(TAG, "Location Updated: " + location.getLatitude() + ", " + location.getLongitude());
                    // Handle the updated location here

                    // Since I an Using the BroadcastReceiver that's why I don't need to handel it here
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }
}
