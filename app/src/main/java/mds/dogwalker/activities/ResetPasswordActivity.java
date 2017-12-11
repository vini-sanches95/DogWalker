package mds.dogwalker.activities;

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
import com.google.firebase.auth.FirebaseAuth;

import mds.dogwalker.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText email;
    private String strEmail;
    private Button btn_Reset;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = (EditText) findViewById(R.id.resetEmail);
        btn_Reset = (Button) findViewById(R.id.btnReset);

        btn_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               strEmail = email.getText().toString();

               if(TextUtils.isEmpty(strEmail)){
                   Toast.makeText(ResetPasswordActivity.this, "Insira seu e-mail cadastrado.",
                                    Toast.LENGTH_SHORT).show();
                    return;
               }
               auth.sendPasswordResetEmail(strEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(ResetPasswordActivity.this, "Enviamos instruções para redefinir sua senha no seu e-mail",  Toast.LENGTH_SHORT).show();
                       } else{
                           Toast.makeText(ResetPasswordActivity.this, "Não conseguimos enviar um e-mail para redefinir sua senha.", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

            }
        });
    }
}
