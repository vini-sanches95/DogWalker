package mds.dogwalker.utils;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by yukio on 12/11/17.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback, LocationListener{

    private LatLng latlng;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

       // googleMap.moveCamera(Cameraupdatefactory.newlatlngzoom(latlng, 15));
    }

    @Override
    public void onLocationChanged(Location location) {
         latlng = new LatLng(location.getLatitude(), location.getLongitude());
         Log.v("Log", "Changed");
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
