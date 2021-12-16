package com.example.customerside;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.customerside.Common.Common;
import com.example.customerside.Pojo.MyPlaces;
import com.example.customerside.Pojo.Results;
import com.example.customerside.RemoteForNearbyPlaces.IGoogleAPIService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindNearMechanic extends FragmentActivity implements OnMapReadyCallback, LocationListener
        ,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMyLocationButtonClickListener {


    private LocationRequest locationRequest;
    private double latitude, longitude;

    private GoogleApiClient client;
    SupportMapFragment mapFragment;
    private Location mLastLocation;
    private GoogleMap mMap;
    private Marker mMarker;
    private int ProximityRadius = 10000;
    private static int UPDATE_INTERVAL = 5000;  // 5 sec
    private static int FASTEST_INTERVAL = 3000;
    private static int DISPLACEMENT = 10;


    IGoogleAPIService mService;


    ImageButton shops_nearby, car_wash_nearby, petrol_pumps_nearby, gym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_near_mechanic);

        shops_nearby = (ImageButton) findViewById(R.id.shops_nearby);
        car_wash_nearby = (ImageButton) findViewById(R.id.car_wash_nearby);
        petrol_pumps_nearby = (ImageButton) findViewById(R.id.petrol_pumps_nearby);
        gym = (ImageButton) findViewById(R.id.gym);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Init Service
        mService = Common.getGoogleAPIService();

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shops_nearby:
                String carRepair = "car_repair";
                String url = getUrl(latitude, longitude, carRepair);
                nearByPlace("car_repair");
               // Toast.makeText(this, "Searching for Nearby MechanicShops...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby MechanicShops...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.car_wash_nearby:
                String carWash = "car_wash";
                url = getUrl(latitude, longitude, carWash);
                nearByPlace("car_wash");
               // Toast.makeText(this, "Searching for Nearby carWash...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby carWash...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.petrol_pumps_nearby:
                String gasStation = "gas_station";
                url = getUrl(latitude, longitude, gasStation);
                nearByPlace("gas_station");
               // Toast.makeText(this, "Searching for Nearby petrolPumps...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby petrolPumps...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gym:
                String gym = "gym";
                url = getUrl(latitude, longitude, gym);
                nearByPlace("gym");
               // Toast.makeText(this, "Searching for Nearby petrolPumps...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby gyms...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void nearByPlace(String placeType) {
        mMap.clear();
        String url = getUrl(latitude, longitude, placeType);

        mService.getNearByPlaces(url)
                .enqueue(new Callback<MyPlaces>() {
                    @Override
                    public void onResponse(Call<MyPlaces> call, Response<MyPlaces> response) {
                        if (response.isSuccessful()) {
                            for (int i = 0; i<response.body().getResults().length; i++) {
                                MarkerOptions markerOptions = new MarkerOptions();
                                Results googlePlace = response.body().getResults()[i];
                                double lat = Double.parseDouble(googlePlace.getGeometry().getLocation().getLat());
                                double lng = Double.parseDouble(googlePlace.getGeometry().getLocation().getLng());
                                String placeName = googlePlace.getName();
                                String vicinity = googlePlace.getVicinity();
                                LatLng latLng = new LatLng(lat,lng);
                                markerOptions.position(latLng);
                                markerOptions.title(placeName);
                                if (placeType.equals("car_repair"))
                                    //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_repairing_car));
                                else if (placeType.equals("car_wash"))
                                    // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_carwash));
                                else if (placeType.equals("gas_station"))
                                    // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_gasstation));
                                else if (placeType.equals("gym"))
                                    // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_dumbell_excersise));
                                else
                                    // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_mechanic_shop));
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                                // Add to Map
                                mMap.addMarker(markerOptions);
                                //Move camera
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MyPlaces> call, Throwable t) {

                    }
                });
    }


    private String getUrl(double latitude, double longitude, String placeType) {
        StringBuilder googleURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location="+latitude+","+longitude);
        googleURL.append("&radius="+ProximityRadius);
        googleURL.append("&type="+placeType);
        googleURL.append("&sensor=true");
        googleURL.append("&key="+"AIzaSyBD5Wgyd9_lXTPxeXpeOUoUdjJ1Davz-vQ");

        Log.d("getUrl", googleURL.toString());

        return googleURL.toString();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*
        try {
            boolean isSuccess = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.uber_style_map)
            );
            if (!isSuccess)
                Log.e("ERROR", "Map Style Load Failed !!!");
        } catch (Resources.NotFoundException ex) {
            ex.printStackTrace();
        }

         */
        mMap = googleMap;
        //   LatLng Lgu = new LatLng(31.4640, 74.4426);
       //   mMap.addMarker(new MarkerOptions().position(Lgu).title("Lahore Garrison University"));
         //   mMap.moveCamera(CameraUpdateFactory.newLatLng(Lgu));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Init Google Play Service
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mMap.setMyLocationEnabled(true);

        //Enable permission for locationEnabled
        mMap.setOnMyLocationButtonClickListener(this);


        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        client.connect();

    }


    @Override
    public boolean onMyLocationButtonClick() {
        checkGPSEnabled();
        return false;
    }


    private void checkGPSEnabled() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true);

        Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    // Toast.makeText(FindNearMechanic.this, "GPS Is Already Running !", Toast.LENGTH_SHORT).show();

                    //request location from device
                } catch (ApiException e) {
                    if (e.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                        try {
                            resolvableApiException.startResolutionForResult(FindNearMechanic.this, 101);
                        } catch (IntentSender.SendIntentException sendIntentException) {
                            sendIntentException.printStackTrace();
                        }
                    }
                    if (e.getStatusCode() == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                        Toast.makeText(FindNearMechanic.this, "Settings not Available", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "GPS Is Enabled", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "GPS Enable Request Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(10f);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client,locationRequest,this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        client.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mMarker != null)
            mMarker.remove();          //Remove Already Marker

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        mMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                .title("Your Location"));

        //Move camera to this position
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 11));
        //Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();

        if (client!=null)
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);

    }

}
