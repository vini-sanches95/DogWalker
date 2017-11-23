package mds.dogwalker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText nome, email, senha;
    private Button btnCadastrar, btnCancelar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnCadastrar = (Button) findViewById(R.id.btn_cadastra);
        btnCancelar = (Button) findViewById(R.id.btn_cancela);
        nome = (EditText) findViewById(R.id.sgnUpNome);
        email = (EditText) findViewById(R.id.sgnUpEmail);
        senha = (EditText) findViewById(R.id.sgnUpSenha);

        auth = FirebaseAuth.getInstance();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.createUserWithEmailAndPassword(email.getText().toString(),senha.getText().toString())
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "O cadastro não pôde ser completado. Verifique validade da senha ou e-mail.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(SignUpActivity.this, AuthenticationActivity.class);
                                    intent.putExtra("emailCadastrado", email.getText().toString());
                                    intent.putExtra("senhaCadastrada", email.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, AuthenticationActivity.class));
                finish();
            }
        });
    }
}
