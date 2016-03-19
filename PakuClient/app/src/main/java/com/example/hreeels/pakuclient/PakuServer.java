package com.example.hreeels.pakuclient;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hreeels on 2016-03-19.
 */
public class PakuServer {

    /**
     * HTTP POST request with simple String.
     *
     * @param aPath
     * @param aMessage
     * @return
     */
    public String postSimpleMessage(String aPath, String aMessage) {
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(aPath);

        List<NameValuePair> lNameValuePair = new ArrayList<NameValuePair>(1);
        lNameValuePair.add(new BasicNameValuePair("message", aMessage));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(lNameValuePair));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse lResponse = httpClient.execute(httpPost);

            Log.d("Http Post Response:", EntityUtils.toString(lResponse.getEntity()));
            return EntityUtils.toString(lResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "Error communicating with server.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error communicating with server.";
        }
    }

    /**
     * HTTP Post request with JSON.
     *
     * @param aPath
     * @param aJSON
     * @return
     */
    public String postJSON(String aPath, String aJSON) {
        HttpClient lHttpClient = new DefaultHttpClient();

        HttpPost lHttpPost = new HttpPost(aPath);

        try {
            lHttpPost.setEntity(new StringEntity(aJSON));
            lHttpPost.setHeader("Accept", "application/json");
            lHttpPost.setHeader("Content-type", "application/json");

            HttpResponse lResponse = lHttpClient.execute(lHttpPost);
            return EntityUtils.toString(lResponse.getEntity());
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Error communicating with server.";
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "Error communicating with server.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error communicating with server.";
        }
    }

}
