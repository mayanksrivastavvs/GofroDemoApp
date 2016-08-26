package com.vs.gofrodemoapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vs.gofrodemoapp.Adapter.ForecastPagerAdapter;
import com.vs.gofrodemoapp.model.forcastPojo.ForecastResponse;
import com.vs.gofrodemoapp.model.forcastPojo.List;
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
    private String lat;
    private String lon;
    private ViewPager forecastPager;
    private ForecastPagerAdapter forecastPagerAdapter;
    private ForecastResponse forecastResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        lat = getIntent().getStringExtra("lat");
        lon = getIntent().getStringExtra("lon");

        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        currentTemperatureField = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);
        weatherIcon = (ImageView) findViewById(R.id.condIcon);

        forecastPager = (ViewPager) findViewById(R.id.forecastPager);
        forecastPager.setPageMargin((int) (getResources().getDisplayMetrics().widthPixels * -0.36));
        forecastPager.setOffscreenPageLimit(5);
        forecastPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override public void transformPage(View page, float position) {
                page.setScaleX(0.7f - Math.abs(position * 0.4f));
                page.setScaleY(0.8f - Math.abs(position * 0.6f));
                page.setAlpha(1.0f - Math.abs(position * 0.5f));
            }
        });
        forecastPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                renderForecastData(forecastResponse,position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final Handler handler = new Handler();
        new Thread() {
            public void run() {
                final String json = FetchWeatherDetail.getJSON(WeatherActivity.this, lat,lon);
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {

                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
//                            WeatherResponse weatherResponse = new Gson().fromJson(json, WeatherResponse.class);
//                            renderWeatherData(weatherResponse);
                             forecastResponse = new Gson().fromJson(json,ForecastResponse.class);
                            renderForecastData(forecastResponse,0);
                            forecastPagerAdapter = new ForecastPagerAdapter(WeatherActivity.this,forecastResponse.getList());
                            if (forecastPager != null) {
                                forecastPager.setAdapter(forecastPagerAdapter);
                            }
                        }
                    });
                }
            }
        }.start();

//        JSONWeatherTask task = new JSONWeatherTask();
//        task.execute(new String[]{"28.582411","77.321090"});

    }

    private void renderForecastData(ForecastResponse forecastResponse ,int position) {

        double tempLong = Math.round(Double.parseDouble(forecastResponse.getList()[position].getMain().getTemp()) - 273.15);
        String temp = String.valueOf(tempLong);
        String humidity = forecastResponse.getList()[position].getMain().getHumidity() + "%";
        String pressure = forecastResponse.getList()[position].getMain().getPressure() + " hPa";
        String city = forecastResponse.getCity().getName() + "," + forecastResponse.getCity().getCountry();
        String windSpeedString = forecastResponse.getList()[position].getWind().getSpeed() + " mps";
        String windDegree = forecastResponse.getList()[position].getWind().getDeg() + "\u00b0";
        String description = forecastResponse.getList()[position].getWeather()[0].getMain() + "(" + forecastResponse.getList()[position].getWeather()[0].getDescription() + ")";

        currentTemperatureField.setText(temp);
        windDeg.setText(windDegree);
        cityText.setText(city);
        condDescr.setText(description);
        hum.setText(humidity);
        press.setText(pressure);
        windSpeed.setText(windSpeedString);
        String imageUrl = "http://openweathermap.org/img/w/%s.png";
        Picasso.with(WeatherActivity.this).load(String.format(imageUrl, forecastResponse.getList()[position].getWeather()[0].getIcon())).into(weatherIcon);

    }

    private void renderWeatherData(WeatherResponse weatherResponse) {
        double tempLong = Math.round(Double.parseDouble(weatherResponse.getMain().getTemp()) - 273.15);
        String temp = String.valueOf(tempLong);
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

