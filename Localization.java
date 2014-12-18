package com.example.lmasiello.sosxlife;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.system.*;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by lmasiello on 16/12/2014.
 */
public class Localization {
    private LocationManager lManager;
    private LocationListener lListener;
    private Location locationGPS;
    private Location locationNTW;
    private Geocoder geo = null;
    public String longitude;
    public String latitude;
    public String altitudine;
    public String accuracy;
    public long timestamp;
    private Integer MAX_MIN = 60 * 1000 * 2 ;
    private Integer MAX_MIN_FOR = 60 * 1000 * 2 ;

    public void Localization (){

    }
    private Boolean getCurrentLocationNTW(Context ctx) {

        lManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        Boolean Enable = lManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (Enable = Boolean.TRUE) {
            locationGPS = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lListener = new LocationListener() {

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    // TODO Auto-generated method stub
                    String a = "STATUS_CHANGE";
                }

                @Override
                public void onProviderEnabled(String provider) {
                    // TODO Auto-generated method stub
                    String a = "Provider_ENABLED";
                }

                @Override
                public void onProviderDisabled(String provider) {
                    // TODO Auto-generated method stub
                    String a = "Provider_Disable";
                }

                @Override
                public void onLocationChanged(Location loc) {
                    // TODO Auto-generated method stub
                    String a = "Location changed";
                    locationGPS = loc;

                    String provider = locationGPS.getProvider();
                    timestamp = locationGPS.getTime();
                    longitude = "Longitudine: " + locationGPS.getLongitude();
                    latitude = "Latitudine: " + locationGPS.getLatitude();
                    altitudine = "Altitudine: " + locationGPS.getAltitude();
                    accuracy = "Precisione: " + locationGPS.getAccuracy();
                }
            };
            lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 100, lListener);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    private Boolean getCurrentLocationGPS(Context ctx) {

        lManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        Boolean Enable = lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (Enable = Boolean.TRUE) {
            locationNTW =  lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            lListener = new  LocationListener() {

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    // TODO Auto-generated method stub
                    String a = "STATUS_CHANGE";
                }

                @Override
                public void onProviderEnabled(String provider) {
                    // TODO Auto-generated method stub
                    String a = "Provider_ENABLED";
                }

                @Override
                public void onProviderDisabled(String provider) {
                    // TODO Auto-generated method stub
                    String a = "Provider_Disable";
                }

                @Override
                public void onLocationChanged(Location loc) {
                    // TODO Auto-generated method stub
                    String a = "Location changed";
                    locationNTW = loc;

                    String provider = locationNTW.getProvider();
                    timestamp = locationNTW.getTime();
                    longitude ="Longitudine: " + locationNTW.getLongitude();
                    latitude ="Latitudine: " + locationNTW.getLatitude();
                    altitudine ="Altitudine: " + locationNTW.getAltitude();
                    accuracy ="Precisione: " + locationNTW.getAccuracy();
                }
            };
            lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 100, lListener);
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }
    public int getPosition(Context ctx, Geocoder geoMain) {
        long diffTimeStamp;
        long FirstCycleForTimestamp;
        Boolean selectModePosition;
        geo = geoMain;
        selectModePosition = this.getCurrentLocationGPS(ctx);
        //while(  location != null  ) {
         //   lManager.removeUpdates(lListener);
         //   this.getCurrentLocation(ctx);
        //}
        if (selectModePosition = Boolean.TRUE) {
            if (locationGPS != null) {

                diffTimeStamp = System.currentTimeMillis() - locationGPS.getTime();
                if (diffTimeStamp > MAX_MIN) {
                    FirstCycleForTimestamp = System.currentTimeMillis();
                    while (diffTimeStamp < MAX_MIN && FirstCycleForTimestamp <= System.currentTimeMillis() + MAX_MIN_FOR) {
                        this.getCurrentLocationGPS(ctx);
                        diffTimeStamp = System.currentTimeMillis() - locationGPS.getTime();
                    }
                    if (diffTimeStamp > MAX_MIN) {

                    }
                }
                lManager.removeUpdates(lListener);
                String provider = location.getProvider();
                timestamp = location.getTime();
                longitude = "Longitudine: " + location.getLongitude();
                latitude = "Latitudine: " + location.getLatitude();
                altitudine = "Altitudine: " + location.getAltitude();
                accuracy = "Precisione: " + location.getAccuracy();
                //traslateAddress(location);
                return 0;
            } else {
                location = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                return -1;
            }
        }
    }
    private String traslateAddress (Location... params){
        Location pos = params[0];
        double latitude = 0;
        double longitude = 0;

        List <Address> addresses = null;
        latitude = pos.getLatitude();
        longitude = pos.getLongitude();
        try {
            addresses = geo.getFromLocation(latitude,longitude,1);
        }
        catch(IOException e){
        }
        if (addresses!=null)
        {
            if (addresses.isEmpty())
            {
               return null;
            }
            else {
                if (addresses.size() > 0)
                {
                    StringBuffer address=new StringBuffer();
                    Address tmp=addresses.get(0);
                    for (int y=0;y<tmp.getMaxAddressLineIndex();y++)
                        address.append(tmp.getAddressLine(y)+"\n");
                    return address.toString();
                }
            }
        }
        return null;
    }
}
