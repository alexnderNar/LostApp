package com.dot.myapplication.Application;

import android.support.v7.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;

import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dot.myapplication.R;
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
public class MyMapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate and return the layout
        View v = inflater.inflate(R.layout.map_fragment, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        String PrimColor = "#" + Integer.toHexString(getResources().getColor(R.color.primary_translucent));

        //Log.d("mylog",PrimColor);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {

            actionBar.setTitle("Map");
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(PrimColor)));
        }
        mMapView.onResume();// needed to get the map to display immediately
        //
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        googleMap = mMapView.getMap();
        // latitude and longitude
        double latitude = 17.385044;
        double longitude = 78.486671;


        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Hello Maps");
        MarkerOptions marker1 = new MarkerOptions().position(
                new LatLng(0, 0)).title("Hello Maps");




        /*// Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(17.385044, 78.486671)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));*/

        // Perform any camera updates here

        return v;
    }

    @Override
    public void onStop() {
        /*String PrimColor = "#" + Integer.toHexString(getResources().getColor(R.color.primary_not_translucent));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(PrimColor)));*/
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        String PrimColor = "#" + Integer.toHexString(getResources().getColor(R.color.primary_not_translucent));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(PrimColor)));
        super.onDestroyView();
    }
}
