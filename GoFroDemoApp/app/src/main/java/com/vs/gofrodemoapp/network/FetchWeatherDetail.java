package com.vs.gofrodemoapp.network;

import android.content.Context;
import android.provider.SyncStateContract;

import com.vs.gofrodemoapp.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Naveen on 24-08-2016.
 */
public class FetchWeatherDetail {
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s";
    private static final String OPEN_WEATHER_FORECAST_API =
            "http://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s";

    public static String getJSON(Context context, String lat,String lon){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_FORECAST_API, lat,lon));
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));
            InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder json = new StringBuilder(1024);
            String tmp;
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return json.toString();
        }catch(Exception e){
            return null;
        }
    }

}
