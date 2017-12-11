package mds.dogwalker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditPetData extends AppCompatActivity {


    String[] animal = {"Cachorro", "Gato", "Coelho", "Cavalo", "Outro"};
    int PICK_IMAGE_REQUEST = 1;
    EditText nomeDoMeuPet, racaPet, pesoPet;
    Spinner tipoDoMeuPet;
    ImageView petPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet_data);

        Spinner spinner = (Spinner) findViewById(R.id.petSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinnertext, animal);
        spinner.setAdapter(adapter);


        nomeDoMeuPet = (EditText)findViewById(R.id.edit_pet_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getActionBar();


        racaPet = (EditText)findViewById(R.id.edit_raca);
        pesoPet = (EditText)findViewById(R.id.edit_peso);
        tipoDoMeuPet = (Spinner)findViewById(R.id.petSpinner);
        petPic = (ImageView)findViewById(R.id.edit_pet_image);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeDoMeuPet.setText((String)extras.get("NomeToEdit"));
            racaPet.setText((String)extras.get("RacaToEdit"));
            pesoPet.setText((String) extras.get("PesoToEdit"));
            //tipoDoMeuPet.setText((String)extras.get("TipoPet"));
            tipoDoMeuPet.setSelection(((ArrayAdapter<String>)tipoDoMeuPet.getAdapter()).getPosition((String)extras.get("TipoToEdit")));
            Bitmap pic = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("PicToEdit"),
                    0, getIntent().getByteArrayExtra("PicToEdit").length);
            petPic.setImageBitmap(pic);
        }
    }

    public void ChangePetPic(View view){
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Escolher Foto"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.edit_pet_image);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SavePet(View view){
        Intent intent = new Intent(this, PetProfileScroll.class);
        intent.putExtra("PetEdit", nomeDoMeuPet.getText().toString());
        intent.putExtra("PetRaca", racaPet.getText().toString());
        intent.putExtra("PesoPet", pesoPet.getText().toString());
        intent.putExtra("TipoPet", tipoDoMeuPet.getSelectedItem().toString());
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        Bitmap pic = ((BitmapDrawable)petPic.getDrawable()).getBitmap();
        pic.compress(Bitmap.CompressFormat.JPEG, 20, bs);
        intent.putExtra("PetPic", bs.toByteArray());
        startActivity(intent);
    }
}
