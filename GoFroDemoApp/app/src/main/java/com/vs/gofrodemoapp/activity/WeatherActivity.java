package com.vs.gofrodemoapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vs.gofrodemoapp.network.FetchWeatherDetail;
import com.vs.gofrodemoapp.R;
import com.vs.gofrodemoapp.model.WeatherResponse;

public class WeatherActivity extends AppCompatActivity {

    private ImageView weatherIcon;
    private TextView currentTemperatureField;
    private TextView windDeg;
    private TextView cityText;
    private TextView condDescr;
    private TextView hum;
    private TextView press;
    private TextView windSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        currentTemperatureField = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);
        weatherIcon = (ImageView) findViewById(R.id.condIcon);
        final Handler handler = new Handler();
        new Thread() {
            public void run() {
                final String json = FetchWeatherDetail.getJSON(WeatherActivity.this, "28.582411", "77.321090");
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {

                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            WeatherResponse weatherResponse = new Gson().fromJson(json, WeatherResponse.class);
                            renderWeatherData(weatherResponse);
                        }
                    });
                }
            }
        }.start();

//        JSONWeatherTask task = new JSONWeatherTask();
//        task.execute(new String[]{"28.582411","77.321090"});

    }

    private void renderWeatherData(WeatherResponse weatherResponse) {
        double tempLong = Math.round(Double.parseDouble(weatherResponse.getMain().getTemp()) - 273.15);
        String temp = String.valueOf(tempLong) +" "+ "\u2103";
        String humidity = weatherResponse.getMain().getHumidity() + "%";
        String pressure = weatherResponse.getMain().getPressure() + " hPa";
        String city = weatherResponse.getName() + "," + weatherResponse.getSys().getCountry();
        String sunRise = weatherResponse.getSys().getSunrise();
        String sunSet = weatherResponse.getSys().getSunset();
        String windSpeedString = weatherResponse.getWind().getSpeed() + " mps";
        String windDegree = weatherResponse.getWind().getDeg() + "\u00b0";
        String description = weatherResponse.getWeather()[0].getMain() + "(" + weatherResponse.getWeather()[0].getDescription() + ")";

        currentTemperatureField.setText(temp);
        windDeg.setText(windDegree);
        cityText.setText(city);
        condDescr.setText(description);
        hum.setText(humidity);
        press.setText(pressure);
        windSpeed.setText(windSpeedString);
        String imageUrl = "http://openweathermap.org/img/w/%s.png";
        Picasso.with(WeatherActivity.this).load(String.format(imageUrl, weatherResponse.getWeather()[0].getIcon())).into(weatherIcon);

    }
}

