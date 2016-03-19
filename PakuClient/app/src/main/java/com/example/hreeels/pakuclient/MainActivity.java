package com.example.hreeels.pakuclient;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    PakuServer iServer;

    TextView iTimestampText;

    Button iCheckInButton;
    Button iCheckOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iServer = new PakuServer();

        iCheckInButton = (Button) findViewById(R.id.check_in_button);
        iCheckOutButton = (Button) findViewById(R.id.check_out_button);
        iTimestampText = (TextView) findViewById(R.id.main_timestamp_text);

        iCheckInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCheckInButton.setClickable(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            initPark("71", "7");
                            iCheckInButton.setClickable(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }
        });

        iCheckOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCheckOutButton.setClickable(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            endPark("71", "7");
                            iCheckOutButton.setClickable(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }
        });
    }

    public void initPark(String aUserID, String aBeaconID) {
        Map<String, String> lReqParms = new HashMap<String, String>();
        lReqParms.put("reqtype", "initpark");
        lReqParms.put("userID", aUserID);
        lReqParms.put("parkingID", aBeaconID);

        String lJSON = new GsonBuilder().create().toJson(lReqParms, Map.class);

        Log.d("test", lJSON);

        String lResposne = iServer.postJSON(Constants.SERVER_DEFAULT_PATH, lJSON);

        Log.d("testInit", lResposne);
    }

    public void endPark(String aUserID, String aBeaconID) {
        Map<String, String> lReqParms = new HashMap<String, String>();
        lReqParms.put("reqtype", "endpark");
        lReqParms.put("userID", aUserID);
        lReqParms.put("parkingID", aBeaconID);

        String lJSON = new GsonBuilder().create().toJson(lReqParms, Map.class);

        Log.d("test", lJSON);

        String lResponse = iServer.postJSON(Constants.SERVER_DEFAULT_PATH, lJSON);

        Log.d("response", lResponse);
    }
}
