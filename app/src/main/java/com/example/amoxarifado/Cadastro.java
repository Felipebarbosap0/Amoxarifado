package com.example.amoxarifado;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference usersRef;

    static Map<String,String> dados = new HashMap<>();
    private EditText editTextNome, editTextEmail, editTextSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        ativar();

    }

    private void ativar() {
        editTextNome = findViewById(R.id.editTextNomeCadastro);
        editTextEmail = findViewById(R.id.editTextEmailCadastro);
        editTextSenha = findViewById(R.id.editTextSenhaCadastro);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dados = new HashMap<>();

    }
    public void btnCadastrar(View view){

        if(editTextNome .getText().toString().isEmpty() ||
                editTextEmail.getText().toString().isEmpty() ||
                editTextSenha.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT).show();

        }else {
            String email = editTextEmail.getText().toString();
            String senha = editTextSenha.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Toast.makeText(getApplicationContext(),"Cadastro Realizado com sucesso!!!",
                                    Toast.LENGTH_SHORT).show();
                            usersRef = database.getReference("User/" + mAuth.getUid()+"/");

                            dados.put("Nome",editTextNome.getText().toString().trim());
                            dados.put("Email",editTextEmail.getText().toString().trim());
                            dados.put("Senha",editTextSenha.getText().toString().trim());

                            usersRef.setValue(dados);
                            Toast.makeText(getApplicationContext(),
                                    "Cadastro Realizado!!!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Main.class);
                            startActivity(intent);


                        }
                    });

        }
    }






}