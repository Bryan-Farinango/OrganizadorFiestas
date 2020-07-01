package com.example.organizadorfiestas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {
    private List<Invitado> listPerson = new ArrayList<Invitado>();
    ArrayAdapter<Invitado> arrayAdapterInvitado;

    ListView lista;
    EditText nombre, edad, celular, instagram;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Invitado invitadoSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        nombre = findViewById(R.id.txtNombre2);
        edad = findViewById(R.id.txtEdad2);
        celular = findViewById(R.id.txtCelular2);
        instagram = findViewById(R.id.txtInstagram2);

        getSupportActionBar().hide();
        lista = findViewById(R.id.listaBase);

        inicializarFirebase();
        listarDatos();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                invitadoSelected = (Invitado) parent.getItemAtPosition(position);
                nombre.setText(invitadoSelected.getNombre());
                edad.setText(invitadoSelected.getEdad());
                celular.setText(invitadoSelected.getCelular());
                instagram.setText(invitadoSelected.getInstagram());

            }
        });
    }
    public void btnActualizar (View v){
        String name = nombre.getText().toString();
        String age = edad.getText().toString();
        String phone = celular.getText().toString();
        String insta = instagram.getText().toString();

        Invitado p = new Invitado();
        p.setUid(invitadoSelected.getUid());
        p.setNombre(nombre.getText().toString().trim());
        p.setEdad(edad.getText().toString().trim());
        p.setCelular(celular.getText().toString().trim());
        p.setInstagram(celular.getText().toString().trim());
        databaseReference.child("Invitado").child(p.getUid()).setValue(p);

    }

    public void btnEliminar(View v){
        Invitado p = new Invitado();
        p.setUid(invitadoSelected.getUid());
        databaseReference.child("Invitado").child(p.getUid()).removeValue();
    }


    public void principal (View v){
        Intent principal = new Intent(this, MainActivity.class);
        startActivity(principal);
    }

    private void listarDatos() {
        databaseReference.child("Invitado").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : snapshot.getChildren()){
                    Invitado p = objSnaptshot.getValue(Invitado.class);
                    listPerson.add(p);
                    arrayAdapterInvitado = new ArrayAdapter<Invitado>( Lista.this, android.R.layout.simple_list_item_1, listPerson);
                    lista.setAdapter(arrayAdapterInvitado);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


}