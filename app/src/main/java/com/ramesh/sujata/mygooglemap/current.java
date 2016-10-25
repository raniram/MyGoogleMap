package com.ramesh.sujata.mygooglemap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;


public class current extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {

    private GoogleMap mMap;
    private StreetViewPanorama mSvp;

    private static final LatLng SAN_FRAN = new LatLng(37.765927, -122.449972);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }


    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {

        mSvp=streetViewPanorama;
        mSvp.setPosition(SAN_FRAN);

        StreetViewPanoramaLocation location = mSvp.getLocation();
        if (location != null && location.links != null) {
            mSvp.setPosition(location.links[0].panoId);
        }


        long duration = 500;
        float tilt = 0;
        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .zoom(mSvp.getPanoramaCamera().zoom)
                .bearing(mSvp.getPanoramaCamera().bearing)
                .tilt(tilt)
                .build();

        mSvp.animateTo(camera, duration);
    }
}
