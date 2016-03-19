package com.example.hreeels.pakuclient;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    PakuServer iServer;

    TextView iTimestampText;
    Button iPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iServer = new PakuServer();

        iPostButton = (Button) findViewById(R.id.post_button);
        iTimestampText = (TextView) findViewById(R.id.main_timestamp_text);

        iPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPostButton.setClickable(false);

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            initPark("hmukherjee", "7");
                            //endPark("hmukherjee", "7");
                            iPostButton.setClickable(true);
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
        lReqParms.put("beaconID", aBeaconID);

        String lJSON = new GsonBuilder().create().toJson(lReqParms, Map.class);

        Log.d("test", lJSON);

        String lResposne = iServer.postJSON(Constants.SERVER_DEFAULT_PATH, lJSON);

        Log.d("testInit", lResposne);
    }

    public void endPark(String aUserID, String aBeaconID) {
        Map<String, String> lReqParms = new HashMap<String, String>();
        lReqParms.put("reqtype", "endpark");
        lReqParms.put("userID", aUserID);
        lReqParms.put("beaconID", aBeaconID);

        String lJSON = new GsonBuilder().create().toJson(lReqParms, Map.class);

        Log.d("test", lJSON);

        String lResponse = iServer.postJSON(Constants.SERVER_DEFAULT_PATH, lJSON);

        Log.d("response", lResponse);
    }
}
