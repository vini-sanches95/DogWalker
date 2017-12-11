package mds.dogwalker.activities;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mds.dogwalker.R;
import mds.dogwalker.models.Caminho;
import mds.dogwalker.models.Passeio;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener{

    private GoogleMap mMap;
    private LatLng latlng;
    LocationManager locationManager;
    Caminho caminho = new Caminho(mMap);
    Passeio passeio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }

    public void AddPoop(Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        passeio.AddCoco(latLng);
        Toast.makeText(MapsActivity.this, "Poo Added!", Toast.LENGTH_SHORT).show();
    }

    public void AddPee(Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng));
        passeio.AddXixi(latLng);
        Toast.makeText(MapsActivity.this, "Pee Added!", Toast.LENGTH_SHORT).show();
    }


    public void Start(View view){
        View stop = findViewById(R.id.StopButton);
        View start = findViewById(R.id.StartButton);
        stop.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
    }

    public void Stop(View view){
        View stop = findViewById(R.id.StopButton);
        View start = findViewById(R.id.StartButton);
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.INVISIBLE);

        caminho.finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

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

