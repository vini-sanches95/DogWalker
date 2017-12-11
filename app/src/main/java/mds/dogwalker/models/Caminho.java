package mds.dogwalker.models;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by yukio on 12/11/17.
 */

class Caminho {
    public GoogleMap mapa;
    public Polyline caminho;
    private List<LatLng> coords = new List<LatLng>();
    private PolylineOptions pontos = new PolylineOptions().color(0xFFCC9900).width(10);

    public void addPonto(LatLng pos){
        coords.add(pos);
        pontos.add(pos);
    }

    public void finish(){
        caminho = mapa.addPolyline(pontos);
    }

    Caminho(GoogleMap map){
        mapa = map;
    }
}
