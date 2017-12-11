package mds.dogwalker.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mds.dogwalker.R;

public class AuthenticationActivity extends AppCompatActivity {

    private EditText email, senha;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        email = (EditText) findViewById(R.id.editAuthEmail);
        senha = (EditText) findViewById(R.id.editAuthSenha);

        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            email.setText(intent.getStringExtra("emailCadastrado"));
            senha.setText(intent.getStringExtra("senhaCadastrada"));
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthenticationActivity.this, SignUpActivity.class));
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = email.getText().toString();
                final String pw = senha.getText().toString();

                if (TextUtils.isEmpty(strEmail)){
                    Toast.makeText(getApplicationContext(), "Insira seu e-mail!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pw)){
                    Toast.makeText(getApplicationContext(),"Insira uma senha!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(strEmail, pw).addOnCompleteListener(AuthenticationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(AuthenticationActivity.this, "E-mail ou senha inválidos!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            startActivity(new Intent(AuthenticationActivity.this, MapsActivity.class));
                        }
                    }
                });
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthenticationActivity.this, ResetPasswordActivity.class));
                finish();
            }
        });

    }
}
