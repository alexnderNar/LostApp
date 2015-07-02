package com.dot.myapplication;

import android.support.v7.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;

import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A fragment that launches other parts of the demo application.
 */
public class ActionMapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.map_fragment, container,
                false);

        //Создаем карту
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        //

        //Работа с ActionBar, делаем его полупрозрачным чтобы была видна карта
        String PrimColor = "#" + Integer.toHexString(getResources().getColor(R.color.primary_translucent));
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {

            actionBar.setTitle("Map");
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(PrimColor)));
        }
        //

        mMapView.onResume();// Для того чтобы карта отображалась сразу же
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Начиная от сюда начинай работать с картой и с БД соответственно:
        /*
            Здесь реализуй добавление отметок на карту. Будет круто если отдельно создашь функцию
            для этого чтобы не впихивать сюда кучу кода.
            P.S. Для начала просто выставь на карту все метки с БД.
         */
        googleMap = mMapView.getMap();
        double latitude = 0.0;
        double longitude = 0.0;
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Hello Maps");
        //////////////////////////////////////////////////////


        return v;
    }

    @Override
    public void onDestroyView() {
        String PrimColor = "#" + Integer.toHexString(getResources().getColor(R.color.primary_not_translucent));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(PrimColor)));
        super.onDestroyView();
    }
}
