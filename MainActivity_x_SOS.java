package com.example.lmasiello.sosxlife;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.Locale;


public class MainActivity_x_SOS extends ActionBarActivity {
    String NumeroDiTelefono;
    String MessageHelp;
    Button buttonInvia;
    Geocoder geo;
    Localization posizione;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_x__sos);

        NumeroDiTelefono = "3931553328";
        MessageHelp = "HELP ME!!";
        posizione = new Localization();
        buttonInvia = (Button) findViewById(R.id.ButtonSOS);
        buttonInvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity_x_SOS.class), 0);
                //SmsManager smsHelp = SmsManager.getDefault();
                //smsHelp.sendTextMessage(NumeroDiTelefono, null,MessageHelp,pi,null);
                geo=new Geocoder(getApplicationContext(), Locale.getDefault());
                posizione.getPosition(getApplicationContext(), geo);
                String posix;
                posix = posizione.longitude + posizione.latitude + posizione.altitudine;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_x__so, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
