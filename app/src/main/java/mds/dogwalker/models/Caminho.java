package mds.dogwalker.models;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by yukio on 12/11/17.
 */

class Caminho {
    public GoogleMap mapa;
    public Polyline caminho;
    private List<LatLng> coords = new List<LatLng>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<LatLng> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] ts) {
            return null;
        }

        @Override
        public boolean add(LatLng latLng) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends LatLng> collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection<? extends LatLng> collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public LatLng get(int i) {
            return null;
        }

        @Override
        public LatLng set(int i, LatLng latLng) {
            return null;
        }

        @Override
        public void add(int i, LatLng latLng) {

        }

        @Override
        public LatLng remove(int i) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<LatLng> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<LatLng> listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List<LatLng> subList(int i, int i1) {
            return null;
        }
    };
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
