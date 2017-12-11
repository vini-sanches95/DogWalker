package mds.dogwalker.utils;

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
import mds.dogwalker.activities.EditPetData;

public class PetProfileScroll extends AppCompatActivity {

    TextView Nome, Raca, Peso, Tipo;
    ImageView Pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Nome = (TextView)findViewById(R.id.nomePet);
        Raca = (TextView)findViewById(R.id.racaPet);
        Peso = (TextView)findViewById(R.id.pesoPet);
        Tipo = (TextView)findViewById(R.id.tipoPet);
        Pic = (ImageView)findViewById(R.id.pet_image);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Nome.setText((String)extras.get("PetEdit"));
            Raca.setText((String)extras.get("PetRaca"));
            Peso.setText((String) extras.get("PesoPet"));
            Tipo.setText((String)extras.get("TipoPet"));
            Bitmap pic = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("PetPic"),
                    0, getIntent().getByteArrayExtra("PetPic").length);
            Pic.setImageBitmap(pic);
        }
    }

    public void EditPet(View view){
        Intent intent = new Intent(this, EditPetData.class);
        intent.putExtra("NomeToEdit", Nome.getText().toString());
        intent.putExtra("RacaToEdit", Raca.getText().toString());
        intent.putExtra("PesoToEdit", Peso.getText().toString());
        intent.putExtra("TipoToEdit", Tipo.getText().toString());
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        Bitmap pic = ((BitmapDrawable)Pic.getDrawable()).getBitmap();
        pic.compress(Bitmap.CompressFormat.JPEG, 20, bs);
        intent.putExtra("PicToEdit", bs.toByteArray());
        startActivity(intent);
    }
}
