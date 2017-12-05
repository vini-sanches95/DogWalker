package mds.dogwalker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileData extends AppCompatActivity {

    String[] genero = {"Masculino", "Faminino", "Outro"};
    int PICK_IMAGE_REQUEST = 1;
    ImageView Picture;
    EditText Nome, Sobrenome, Email, Senha;
    Spinner Genero;
    EditText Pais;
    //Button btn = (Button)findViewById(R.id.saveBtn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_data);

        Spinner spinner = (Spinner) findViewById(R.id.generoSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinnertext, genero);
        spinner.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Nome  = (EditText)findViewById(R.id.edit_nome);
        Sobrenome = (EditText)findViewById(R.id.edit_sobrenome);
        Email = (EditText)findViewById(R.id.edit_email);
        Senha = (EditText)findViewById(R.id.edit_senha);
        Genero = (Spinner)findViewById(R.id.generoSpinner);
        Pais = (EditText)findViewById(R.id.edit_pais);
        Picture = (ImageView) findViewById(R.id.edit_profile_image);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Nome.setText((String)extras.get("NomeToEdit"));
            Sobrenome.setText((String)extras.get("SobrenomeToEdit"));
            Email.setText((String) extras.get("EmailToEdit"));
            Senha.setText((String)extras.get("SenhaToEdit"));
            Bitmap pic = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("PictureToEdit"),
                    0, getIntent().getByteArrayExtra("PictureToEdit").length);
            Picture.setImageBitmap(pic);
            Genero.setSelection(((ArrayAdapter<String>)Genero.getAdapter()).getPosition((String)extras.get("GeneroToEdit")));
            Pais.setText((String)extras.get("PaisToEdit"));
        }

    }

    public void ChangeProfilePic(View view){
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

                ImageView imageView = (ImageView) findViewById(R.id.edit_profile_image);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SaveAndGoToProfile(View view){
        //Profile obj = new Profile(0, userName.getText().toString(), Sobrenome.getText().toString(), ((BitmapDrawable)Picture.getDrawable()).getBitmap(), Senha.getText().toString(), new Date(), Email.getText().toString(), genero.toString());
        Intent intent = new Intent(this, ScrollingActivity.class);
        //intent.putExtra("ProfileData", obj);
        intent.putExtra("ProfileName", Nome.getText().toString());
        intent.putExtra("ProfileSurname", Sobrenome.getText().toString());
        intent.putExtra("ProfileEmail", Email.getText().toString());
        intent.putExtra("ProfileSenha", Senha.getText().toString());
        intent.putExtra("ProfileGenero", Genero.getSelectedItem().toString());
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        Bitmap pic = ((BitmapDrawable)Picture.getDrawable()).getBitmap();
        pic.compress(Bitmap.CompressFormat.JPEG, 20, bs);
        intent.putExtra("ProfilePicture", bs.toByteArray());
        intent.putExtra("ProfilePais", Pais.getText().toString());

        startActivity(intent);
    }
}
