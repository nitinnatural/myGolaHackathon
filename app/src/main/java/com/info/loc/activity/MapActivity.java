package com.info.loc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.info.loc.R;

/**
 * Created by ranjan on 7/26/2015.
 */
public class MapActivity extends Activity implements OnMapReadyCallback {

    Marker markerBangalore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapfragment =(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapfragment.getView().setVisibility(View.VISIBLE);
        mapfragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng bangalore = new LatLng(12.9667, 77.5667);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bangalore, 5));
        markerBangalore = googleMap.addMarker(new MarkerOptions().title("Bangalore").snippet("Silicon Valley of India").position(bangalore).icon	(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTitle().equals("Bangalore")) {
                    Toast.makeText(getBaseContext(), "you are moved to Delhi", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }
}
