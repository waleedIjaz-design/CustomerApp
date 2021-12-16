package com.example.customerside.Common;

import android.location.Location;

import com.example.customerside.Model.Rider;
import com.example.customerside.Remote.FCMClient;
import com.example.customerside.Remote.GoogleMapAPI;
import com.example.customerside.Remote.IFCMService;
import com.example.customerside.Remote.IGoogleAPI;
import com.example.customerside.RemoteForNearbyPlaces.IGoogleAPIService;
import com.example.customerside.RemoteForNearbyPlaces.RetrofitCLient;

public class Common {


    public static final int PICK_IMAGE_REQUEST = 9999;
    public static boolean isDriverFound = false;
    public static String driverId = "";

    public static Location mLastLocation;

    public static Rider currentUser = new Rider();

    public static final String BROADCAST_DROP_OFF = "arrived";

    public static final String CANCEL_BROADCAST_STRING = "cancel_pickup";

    public static final String driver_tbl ="Drivers";
    public static final String user_driver_tbl ="DriversInformation";
    public static final String user_rider_tbl ="RidersInformation";
    public static final String pickup_request_tbl ="PickupRequest";
    public static final String token_tbl ="Tokens";
    public static final String rate_detail_tbl ="RateDetails";



   // public static final String user_field = "rider_usr";
   // public static final String pwd_field = "rider_pwd";


    public static final String fcmURL = "https://fcm.googleapis.com/";

    public static final String googleAPIUrl = "https://maps.googleapis.com";

    //......................
   // public static final String GOOGLE_API_URL = "https://maps.googleapis.com"; //LATEST for near places

    private static double base_fare = 100;
    private static double time_rate = 3.87;
    private static double distance_rate = 10.67;

    public static double getPrice(double km, int min)
    {
        return (base_fare+(time_rate*min)+(distance_rate*km));
    }

    public static IFCMService getFCMService()
    {
        return FCMClient.getClient(fcmURL).create(IFCMService.class);
    }


    public static IGoogleAPI getGoogleService()
    {
        return GoogleMapAPI.getClient(googleAPIUrl).create(IGoogleAPI.class);
    }

//........................................................
    public static IGoogleAPIService getGoogleAPIService()
    {
        return RetrofitCLient.getClient(googleAPIUrl).create(IGoogleAPIService.class);   // LATEST for near places
    }
}
