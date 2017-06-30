package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.content.Intent.EXTRA_TEXT;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mTVWeatherData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mTVWeatherData = (TextView) findViewById(R.id.tv_weather_data);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_TEXT))
            mTVWeatherData.setText(intent.getStringExtra(EXTRA_TEXT));
        // TODO (2) Display the weather forecast that was passed from MainActivity
    }
}