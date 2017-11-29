package mds.dogwalker;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by ViniciusSanches on 23/11/17.
 */

enum animal {Cachorro, Gato, Coelho, Cavalo, Outro}

public class Pet {

    int id;
    Bitmap picture;
    String nome;
    animal tipoAnimal;
    String raca;
    Date nascimento;
    tipoGenero genero;
    boolean castrado;
    float peso;

    Pet(int i, String n, Bitmap pic, animal a, String r, float p, Date nasc, tipoGenero g, boolean c){
        id = i;
        nome = n;
        tipoAnimal = a;
        raca = r;
        peso = p;
        castrado = c;
        genero = g;
        nascimento = nasc;
        picture = pic;
    }

    Pet(){}

    public void SetNascimento(Date n){
        nascimento = n;
    }

    public Date GetNascimento(){
        return nascimento;
    }

    public void SetCastrado(boolean c){
        castrado = c;
    }

    public boolean GetCastrado(){
        return castrado;
    }

    public void SetGenero(tipoGenero g){
        genero = g;
    }

    public tipoGenero GetGenero(){
        return genero;
    }

    public void SetNome(String n){
        nome = n;
    }

    public void SetAnimal(animal a){
        tipoAnimal = a;
    }

    public void SetRaca(String r){
        raca = r;
    }

    public void SetPeso(float p){
        peso = p;
    }

    public String GetNome(){
        return nome;
    }

    public String GetRaca(){
        return raca;
    }

    public animal GetAnimal(){
        return tipoAnimal;
    }

    public float GetPeso(){
        return peso;
    }

    public void SetPicture(Bitmap pic){
        picture = pic;
    }

    public Bitmap GetPicture(){
        return picture;
    }

}
