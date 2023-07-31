package com.example.amoxarifado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeAdm extends AppCompatActivity {
    ListView list;
    List<String> nomes;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    static Map<String, String> dados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_adm);
        getSupportActionBar().hide();
        ativar();

        pegarChaves();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Adapyter adapter = new Adapyter(getApplicationContext(),nomes);
                list.setAdapter(adapter);


            }
        },10000);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (int i = 0; i < nomes.size(); i++){
                    if (i == position){


                        dados.put("Nome", nomes.get(position));


                        Intent intent = new Intent(getApplicationContext(), Dados.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }

    private void pegarChaves() {

        myRef = database.getReference("User/" + mAuth.getUid() + "/Item/");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot key : snapshot.getChildren()) {
                    String nome = key.getKey();
                    nomes.add(nome);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ativar() {
        list = findViewById(R.id.list) ;
        nomes = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dados = new HashMap<>();
    }

    public void btnItem(View view){
        Intent intent = new Intent(HomeAdm.this, CriacaoItem.class);
        startActivity(intent);
    }

    public void btnSair(View view){
        Intent intent = new Intent(getApplicationContext(), Main.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();

    }
}