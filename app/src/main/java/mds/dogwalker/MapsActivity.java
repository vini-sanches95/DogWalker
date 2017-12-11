package mds.dogwalker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void AddPoop(View view){
        Toast.makeText(MapsActivity.this, "Poo Added!", Toast.LENGTH_SHORT).show();
    }

    public void AddPee(View view){
        Toast.makeText(MapsActivity.this, "Pee Added!", Toast.LENGTH_SHORT).show();
    }

    public void BurgerMenu(View view){
        Toast.makeText(MapsActivity.this, "Burger menu works!", Toast.LENGTH_SHORT).show();
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
    }

}

