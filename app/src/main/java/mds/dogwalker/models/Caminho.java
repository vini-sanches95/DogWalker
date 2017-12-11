package mds.dogwalker.models;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by yukio on 12/11/17.
 */

public class Caminho {
    public GoogleMap mapa;
    public Polyline caminho;
    private List<LatLng> coords;
    private PolylineOptions pontos = new PolylineOptions().color(0xFFCC9900).width(10);

    public void addPonto(LatLng pos){
        coords.add(pos);
        pontos.add(pos);
    }

    public void addPee(LatLng coord){
        mapa.addMarker(new MarkerOptions().position(coord).title("Iih, xixi!"));
    }

    public void addPoop(LatLng coord){
        mapa.addMarker(new MarkerOptions().position(coord).title("Ooh, cocô!"));
    }

    public void finish(){
        caminho = mapa.addPolyline(pontos);
    }

    public Caminho(GoogleMap map){
        mapa = map;
    }
}
