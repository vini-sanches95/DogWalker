package mds.dogwalker;

import java.sql.Time;
import java.util.List;

/**
 * Created by ViniciusSanches on 23/11/17.
 */

public class Passeio {

    int id;
    float distancia;
    Caminho caminho;
    Time duracao;
    Profile usuario;
    List<Pet> pets;
    int coco;
    int xixi;

    Passeio(){}

    Passeio(int i, float d, Caminho c, Time t, int co, int x, Profile u){
        id = i;
        distancia = d;
        caminho = c;
        duracao = t;
        usuario = u;
        coco = co;
        xixi = x;
    }

    public List<Pet> GetPets(){
        return  pets;
    }

    public void AddCoco(){
        coco++;
    }

    public void AddXixi(){
        xixi++;
    }

    public int GetCoco(){
        return coco;
    }

    public int GetXixi(){
        return xixi;
    }

    public void SetUsuario(Profile u){
        usuario = u;
    }

    public Profile GetUsuario(){
        return usuario;
    }

    public void SetDistancia(float d){
        distancia = d;
    }

    public void SetCaminho(Caminho c){
        caminho = c;
    }

    public void SetDuracao(Time t){
        duracao = t;
    }

    public Time GetDuracao(){
        return duracao;
    }

    public float GetDistancia(){
        return distancia;
    }

    public Caminho GetCaminho(){
        return caminho;
    }
}
