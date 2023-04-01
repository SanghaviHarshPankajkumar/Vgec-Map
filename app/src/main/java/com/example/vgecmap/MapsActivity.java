package com.example.vgecmap;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.vgecmap.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    //    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    //    private FusedLocationProviderClient mFusedLocationClient;
//    private LocationCallback mLocationCallback;
    private static final String TAG = "MapsActivity";
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    final Polyline[] previousPolyline = {null};
    private GoogleMap mMap;
    private  boolean issecond= true;
    private Marker mSelectedMarker;
    private Location UserLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;

    private List<Geofence> mGeofenceList = new ArrayList<>();
    private GeofencingClient mGeofencingClient;
    private PendingIntent mGeofencePendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
//        // Set up LocationCallback to handle location updates
//        mLocationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                // Move camera to new location
//                Location location = locationResult.getLastLocation();
//                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            }
//        };
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
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
//        mMap.setMyLocationEnabled(true);
//        mFusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
//            if (location != null) {
//                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//            }
//        });
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        startLocationUpdates();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopLocationUpdates();
//    }
//
//    private void startLocationUpdates() {
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
//        mFusedLocationClient.requestLocationUpdates(LocationServices.getGeofencingClient(this).getGeofencingRequest(), mLocationCallback, null);
//    }
//
//    private void stopLocationUpdates() {
//        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
//    }
//
//    @Override
//    public void onMapClick(LatLng latLng) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Initialize FusedLocationProviderClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Initialize GeofencingClient
        mGeofencingClient = LocationServices.getGeofencingClient(this);

        // Build LocationCallback
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                 mMap.clear();
                // Move camera to current location
                Log.i("why","yess this i can see..");
                ArrayList<Marker> predefinedMarkers = new ArrayList<>();

                Location location = locationResult.getLastLocation();
                UserLocation = location;
                LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                 mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Current Location"));
                 //MarkerOptions marker =new MarkerOptions().position(currentLatLng).title("Current Location");
                 Marker m = mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Current Location"));

                 LatLng B_block = new LatLng(23.10632171, 72.59517923);
                Marker bBlock = mMap.addMarker(new MarkerOptions().position(B_block).title("C Block").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
               // mMap.moveCamera(CameraUpdateFactory.newLatLng(B_block));

                LatLng C_block = new LatLng(23.10666863, 72.59487565);
                Marker cBlock = mMap.addMarker(new MarkerOptions().position(C_block).title("C Block").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
               // mMap.moveCamera(CameraUpdateFactory.newLatLng(C_block));


                LatLng G_block = new LatLng(23.10731219, 72.59431883);
                Marker gBlock = mMap.addMarker(new MarkerOptions().position(G_block).title("G Block").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
              //  mMap.moveCamera(CameraUpdateFactory.newLatLng(G_block));

                LatLng K_block = new LatLng(23.10718958, 72.59442938);
                Marker kBlock = mMap.addMarker(new MarkerOptions().position(K_block).title("K Block").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
              //  mMap.moveCamera(CameraUpdateFactory.newLatLng(K_block));

                LatLng M_block = new LatLng(23.10804162, 72.59403184);
                Marker mBlock = mMap.addMarker(new MarkerOptions().position(M_block).title("M Block").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
               // mMap.moveCamera(CameraUpdateFactory.newLatLng(M_block));

                LatLng N_block = new LatLng(23.10805975, 72.59473012);
                Marker nBlock = mMap.addMarker(new MarkerOptions().position(N_block).title("N Block").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
              //  mMap.moveCamera(CameraUpdateFactory.newLatLng(N_block));

                LatLng W_block = new LatLng(23.10807334, 72.59520672);
                Marker wBlock = mMap.addMarker(new MarkerOptions().position(W_block).title("W Block").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
               // mMap.moveCamera(CameraUpdateFactory.newLatLng(W_block));

               if(issecond){
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 19));
                   issecond= false;}
                LatLng nextLocation = new LatLng(23.10636402, 72.59452837);
                mMap.addMarker(new MarkerOptions().position(nextLocation).title("Bus Station"));
                drawRoute(currentLatLng, nextLocation);
                predefinedMarkers.add(m);

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        LatLng markerLatLng = marker.getPosition();
                        PolylineOptions polylineOptions = new PolylineOptions()
                                .add(markerLatLng)
                                .color(Color.BLUE)
                                .width(10)
                                .pattern(Arrays.asList(new Dot(), new Gap(13f)));
                        for (Marker predefinedMarker : predefinedMarkers) {
                            if (predefinedMarker.getTitle().equals(marker.getTitle())) {
                                continue; // Ignore the clicked marker itself
                            }
                            LatLng predefinedMarkerLatLng = predefinedMarker.getPosition();
                            polylineOptions.add(predefinedMarkerLatLng);
                        }
//                        if (previousPolyline[0] != null) {
//                            previousPolyline[0].remove(); // remove the previously added polyline
//                        }
                         mMap.addPolyline(polylineOptions); // keep a reference to the new polyline
                        return true;
                    }
                });
            }
        };

        // Build GoogleMap
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng curr =  new LatLng(UserLocation.getLatitude(),UserLocation.getLongitude());
//        mMap.addCircle(new CircleOptions().center(curr));

        // Set OnMapClickListener
        mMap.setOnMapClickListener(this);

        // Create LocationRequest
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(7000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // Add marker to clicked location
        if (mSelectedMarker != null) {
            mSelectedMarker.remove();
        }
        mSelectedMarker = mMap.addMarker(new MarkerOptions().position(latLng));

        // Add geofence to selected location
        Geofence geofence = new Geofence.Builder()
                .setRequestId("Geofence")
                .setCircularRegion(latLng.latitude, latLng.longitude, 100)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
        mGeofenceList.add(geofence);
        if (mGeofencePendingIntent != null) {
            mGeofencingClient.removeGeofences(mGeofencePendingIntent);
        }
        mGeofencePendingIntent = getGeofencePendingIntent();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGeofencingClient.addGeofences(getGeofencingRequest(), mGeofencePendingIntent)
                .addOnSuccessListener(aVoid -> Log.i(TAG, "Geofences added"))
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Failed to add geofences: " + e.getMessage());
                    if (e instanceof ApiException) {
                        ApiException apiException = (ApiException) e;
                        int statusCode = apiException.getStatusCode();
                        if (statusCode == GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE) {
                            Log.e(TAG, "Geofencing not available");
                        } else if (statusCode == GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES) {
                            Log.e(TAG, "Too many geofences");
                        } else if (statusCode == GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS) {
                            Log.e(TAG, "Too many pending intents");
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if location permissions are granted
        if (!checkPermissions()) {
            requestPermissions();
        } else {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null /* Looper */);
    }

    private void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    private PendingIntent getGeofencePendingIntent() {
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
        mGeofencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return mGeofencePendingIntent;
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }

    private void drawRoute(LatLng origin, LatLng destination) {
        // Define the API client
        Log.i("why","in 0 the try");

        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey("AIzaSyBPI0cB6QY-sJZf0z8_CuaS6G5fEHiGkig")
                .build();

        // Define the origin and destination points

        // Define the request parameters
        Log.i("why","in 1 the try");

        DirectionsApiRequest directionsApiRequest = new DirectionsApiRequest(geoApiContext);
        directionsApiRequest.origin(String.valueOf(origin));
        directionsApiRequest.destination(String.valueOf(destination));
        directionsApiRequest.mode(TravelMode.DRIVING);
        Log.i("why","in 2 the try");

        // Execute the request
        try {
            DirectionsResult directionsResult = directionsApiRequest.await();
            Log.i("why","in 3 the try");
            // Extract the route points from the result
            List<LatLng> points = new ArrayList<>();
            List<com.google.maps.model.LatLng> path = directionsResult.routes[0].overviewPolyline.decodePath();
            for (com.google.maps.model.LatLng latLng : path) {
                points.add(new LatLng(latLng.lat, latLng.lng));
            }

            // Draw the route on the map
            PolylineOptions polylineOptions = new PolylineOptions()
                    .addAll(points)
                    .color(Color.BLUE)
                    .width(5);
            mMap.addPolyline(polylineOptions);

        } catch (Exception e) {
            Log.i("why","in side the catch");

            e.printStackTrace();
        }
    }




}



