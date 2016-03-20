package com.example.hreeels.pakuclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.sdk.SystemRequirementsChecker;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    PakuServer iServer;

    TextView iAppTitle;
    TextView iMainCaption;
    TextView iParkSpotNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        iServer = new PakuServer();

        iAppTitle = (TextView) findViewById(R.id.main_title);
        iMainCaption = (TextView) findViewById(R.id.main_caption);
        iParkSpotNumber = (TextView) findViewById(R.id.parking_spot_number);

        decorateComponents();

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        iParkSpotNumber.setText("7");
                        parkingCheckIn();
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to park in spot 7?").setPositiveButton("Fuck yeah!", dialogClickListener)
                .setNegativeButton("Nah, Fam.", dialogClickListener).show();
    }

    public void parkingCheckIn() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    initPark("71", "7");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    public void parkingCheckOut() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    endPark("71", "7");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
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

    public void decorateComponents() {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "bebas_neue_regular.ttf");
        Typeface customFontBold = Typeface.createFromAsset(getAssets(), "bebas_neue_bold.ttf");

        iAppTitle.setTypeface(customFontBold);
        iMainCaption.setTypeface(customFont);
        iParkSpotNumber.setTypeface(customFontBold);

        String lText = iAppTitle.getText().toString();
        iAppTitle.setText(lText.toUpperCase());

        lText = iMainCaption.getText().toString();
        iMainCaption.setText(lText.toUpperCase());
    }
}
