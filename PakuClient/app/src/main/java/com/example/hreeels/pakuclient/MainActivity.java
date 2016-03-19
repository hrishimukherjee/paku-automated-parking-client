package com.example.hreeels.pakuclient;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView iTimestampText;
    Button iPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPostButton = (Button) findViewById(R.id.post_button);
        iTimestampText = (TextView) findViewById(R.id.main_timestamp_text);

        iPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            sendPostRequest("sup, fam?");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }
        });
    }

    public void sendPostRequest(String aMessage) {
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost("http://192.168.1.105:2116");


        List<NameValuePair> lNameValuePair = new ArrayList<NameValuePair>(1);
        lNameValuePair.add(new BasicNameValuePair("message", aMessage));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(lNameValuePair));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse lResponse = httpClient.execute(httpPost);

            iTimestampText.setText(EntityUtils.toString(lResponse.getEntity()));
            Log.d("Http Post Response:", EntityUtils.toString(lResponse.getEntity()));
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }

    }

    private void makeGetRequest() {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://192.168.1.100:2116");
        // replace with your url

        HttpResponse response;
        try {
            response = client.execute(request);

            Log.d("Response of GET request", response.toString());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
