package mds.dogwalker;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ViniciusSanches on 23/11/17.
 */

enum tipoGenero {Masculino, Feminino, Outro}

public class Profile implements Parcelable {

    int id;
    Bitmap picture;
    String nome;
    String sobrenome;
    String senha;
    Date nascimento;
    String email;
    String genero;
    List<Pet> pets;
    //String local;

    Profile(){}

    Profile(int i, String n, String s, Bitmap pic, String p, Date nasc, String e, String g){
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

    public void SetEmail(String e){
        email = e;
    }

    public void SetGenero(String g){
        genero = g;
    }

    public String GetNome(){
        return nome;
    }

    public String GetSobrenome(){
        return sobrenome;
    }

    public String GetEmail(){
        return email;
    }

    public String GetGenero(){
        return genero;
    }

    public void SetPicture(Bitmap pic){
        picture = pic;
    }

    public Bitmap GetPicture(){
        return picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.picture, flags);
        dest.writeString(this.nome);
        dest.writeString(this.sobrenome);
        dest.writeString(this.senha);
        dest.writeLong(this.nascimento != null ? this.nascimento.getTime() : -1);
        dest.writeString(this.email);
        dest.writeString(this.genero);
        dest.writeList(this.pets);
    }

    protected Profile(Parcel in) {
        this.id = in.readInt();
        this.picture = in.readParcelable(Bitmap.class.getClassLoader());
        this.nome = in.readString();
        this.sobrenome = in.readString();
        this.senha = in.readString();
        long tmpNascimento = in.readLong();
        this.nascimento = tmpNascimento == -1 ? null : new Date(tmpNascimento);
        this.email = in.readString();
        this.genero = in.readString();
        this.pets = new ArrayList<Pet>();
        in.readList(this.pets, Pet.class.getClassLoader());
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
