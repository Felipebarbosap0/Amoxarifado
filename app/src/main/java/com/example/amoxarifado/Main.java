package com.example.amoxarifado;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main extends AppCompatActivity {

    private EditText editTextUser, editTextSenha;
    private FirebaseAuth mAuth;
    CheckBox ms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ativar();
    }

    private void ativar() {
        editTextUser = findViewById(R.id.editTextEmailLogin);
        editTextSenha = findViewById(R.id.editTextSenhaLogin);
        mAuth =  FirebaseAuth.getInstance();
        ms = findViewById(R.id.ms);

    }
    public void  ms(View view){
        if (ms.isChecked()) {
            editTextSenha.setInputType(1);
        } else {
            editTextSenha.setInputType(129);
        }
    }
    public void btnLogin(View view){
        String email = editTextUser.getText().toString();
        String senha = editTextSenha.getText().toString();



        atenUser(email, senha);

        Intent ie = new Intent(Main.this, HomeAdm.class);
        startActivity(ie);


        Toast.makeText(Main.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

    }

    private void atenUser(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {




                        } else {
                            Toast.makeText(Main.this, "Falha ao fazer login. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void btnCadastro(View view){
        Intent intent = new Intent(Main.this, Cadastro.class);
        startActivity(intent);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        Log.d("user", String.valueOf(currentUser));

        if (currentUser != null) {
            // Se já houver um usuário logado, redireciona para a tela Home
            Intent intent = new Intent(getApplicationContext(), HomeAdm.class);
            startActivity(intent);
        }
    }

}