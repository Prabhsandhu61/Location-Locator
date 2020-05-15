package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCity extends AppCompatActivity {

    EditText cityna;
    EditText longit;
    EditText latit;
    Button add;
    Boolean editPage = false;
    CityDataSource cityDataSource;
    City city;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

            cityna = findViewById(R.id.city);
            longit = findViewById(R.id.longitude);
            latit = findViewById(R.id.latitude);
            add = findViewById(R.id.add);
            cityDataSource = new CityDataSource(this);
            if (!cityDataSource.isOpen()) cityDataSource.open();
                city = new City();
        }

        @Override
        protected void onResume() {
            super.onResume();
            if (!cityDataSource.isOpen()) cityDataSource.open();
        }

        @Override
        protected void onPause() {
            super.onPause();
        }

        public void add(View view)
        {
            if(cityna.getText().toString().equals(""))
            {
                Toast toast=Toast. makeText(getApplicationContext(),"Enter City Name",Toast. LENGTH_SHORT);
                toast.show();
            }
            else if(longit.getText().toString().equals(""))
            {
                Toast toast=Toast. makeText(getApplicationContext(),"Enter Longitude",Toast. LENGTH_SHORT);
                toast.show();
            }
            else if(latit.getText().toString().equals(""))
            {
                Toast toast=Toast. makeText(getApplicationContext(),"Enter Latitude",Toast. LENGTH_SHORT);
                toast.show();
            }
            else {
                city.setCity(cityna.getText().toString());
                city.setLongitude(Float.parseFloat(longit.getText().toString()));
                city.setLatitude(Float.parseFloat(latit.getText().toString()));
                city.setId(cityDataSource.getCityCount() + 1);
                cityDataSource.addCity(city);
                Intent intent = new Intent(getApplicationContext(), cityList.class);
                startActivity(intent);
            }
        }
    }