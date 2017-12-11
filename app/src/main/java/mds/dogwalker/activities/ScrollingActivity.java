package mds.dogwalker.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import mds.dogwalker.R;

public class ScrollingActivity extends AppCompatActivity {

    TextView userName, Sobrenome, Email, Senha, Genero, Pais;
    ImageView Picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        userName = (TextView) findViewById(R.id.user_name);
        Sobrenome = (TextView)findViewById(R.id.sobrenome);
        Email = (TextView)findViewById(R.id.email);
        Senha = (TextView)findViewById(R.id.senha);
        Genero = (TextView)findViewById(R.id.genero);
        Pais = (TextView)findViewById(R.id.pais);
        Picture = (ImageView) findViewById(R.id.profile_image);



        Bundle extras = getIntent().getExtras();
        if (extras != null){
            userName.setText((String)extras.get("ProfileName"));
            Sobrenome.setText((String)extras.get("ProfileSurname"));
            Email.setText((String)extras.get("ProfileEmail"));
            Senha.setText((String)extras.get("ProfileSenha"));
            Genero.setText((String)extras.get("ProfileGenero"));
            Bitmap pic = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("ProfilePicture"),
                    0, getIntent().getByteArrayExtra("ProfilePicture").length);
            Picture.setImageBitmap(pic);
            Pais.setText((String) extras.get("ProfilePais"));

        }

    }

    public void EditProfile(View view){
        Intent intent = new Intent(this, EditProfileData.class);
        intent.putExtra("NomeToEdit", userName.getText().toString());
        intent.putExtra("SobrenomeToEdit", Sobrenome.getText().toString());
        intent.putExtra("EmailToEdit", Email.getText().toString());
        intent.putExtra("SenhaToEdit", Senha.getText().toString());
        intent.putExtra("GeneroToEdit", Genero.getText().toString());
        intent.putExtra("PaisToEdit", Pais.getText().toString());
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        Bitmap pic = ((BitmapDrawable)Picture.getDrawable()).getBitmap();
        pic.compress(Bitmap.CompressFormat.JPEG, 20, bs);
        intent.putExtra("PictureToEdit", bs.toByteArray());
        startActivity(intent);
    }
}
