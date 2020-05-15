package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<City> {

    Context context;
    List<City> list;
    LayoutInflater inflater;

    public ListAdapter(@NonNull Context context, List<City> list) {
        super(context, R.layout.listview_layout, list);//
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater.from(context));//
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = inflater.inflate(R.layout.listview_layout, null);
        TextView cityname = view.findViewById(R.id.cityname);
        TextView longitud = view.findViewById(R.id.longi);
        TextView latitud = view.findViewById(R.id.lati);
        cityname.setText(list.get(position).getCity());
        longitud.setText(String.valueOf(list.get(position).getLongitude()));
        latitud.setText(String.valueOf(list.get(position).getLatitude()));

        return view;
    }
}
