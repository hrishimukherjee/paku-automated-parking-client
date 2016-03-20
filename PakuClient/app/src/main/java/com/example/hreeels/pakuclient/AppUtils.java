package com.example.hreeels.pakuclient;

import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * Created by Hreeels on 2016-03-19.
 */
public class AppUtils {

    public static String createJSONString(Map<String, String> aValues) {
        return new GsonBuilder().create().toJson(aValues, Map.class);
    }

}
