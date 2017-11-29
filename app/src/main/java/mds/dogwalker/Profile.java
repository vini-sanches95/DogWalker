package mds.dogwalker;

import android.graphics.Bitmap;
import android.provider.ContactsContract;

import java.util.Date;
import java.util.List;

/**
 * Created by ViniciusSanches on 23/11/17.
 */

enum tipoGenero {Masculino, Feminino, Outro}

public class Profile {

    int id;
    Bitmap picture;
    String nome;
    String sobrenome;
    String senha;
    Date nascimento;
    ContactsContract.CommonDataKinds.Email email;
    tipoGenero genero;
    List<Pet> pets;
    //String local;

    Profile(){}

    Profile(int i, String n, String s, Bitmap pic, String p, Date nasc, ContactsContract.CommonDataKinds.Email e, tipoGenero g){
        id = i;
        nome = n;
        sobrenome = s;
        email = e;
        genero = g;
        senha = p;
        nascimento = nasc;
        picture = pic;
    }

    public List<Pet> GetPets(){
        return pets;
    }

    public void SetSenha(String s){
        senha = s;
    }

    public String GetSenha(){
        return senha;
    }

    public void SetDataNascimento(Date n){
        nascimento = n;
    }

    public Date GetDataNascimento(){
        return nascimento;
    }

    public void SetNome(String n){
        nome = n;
    }

    public void SetSobrenome(String s){
        sobrenome = s;
    }

    public void SetEmail(ContactsContract.CommonDataKinds.Email e){
        email = e;
    }

    public void SetGenero(tipoGenero g){
        genero = g;
    }

    public String GetNome(){
        return nome;
    }

    public String GetSobrenome(){
        return sobrenome;
    }

    public ContactsContract.CommonDataKinds.Email GetEmail(){
        return email;
    }

    public tipoGenero GetGenero(){
        return genero;
    }

    public void SetPicture(Bitmap pic){
        picture = pic;
    }

    public Bitmap GetPicture(){
        return picture;
    }
}
