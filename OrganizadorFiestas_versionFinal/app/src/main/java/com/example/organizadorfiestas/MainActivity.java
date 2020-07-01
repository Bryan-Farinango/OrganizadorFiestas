package com.example.organizadorfiestas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {



    EditText nombre, edad, celular, instagram;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        nombre = findViewById(R.id.txtNombre);
        edad = findViewById(R.id.txtEdad);
        celular = findViewById(R.id.txtCelular);
        instagram = findViewById(R.id.txtInstagram);
        inicializarFirebase();

    }



    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void listado(View v){
        Intent listado = new Intent(this, Lista.class);
        startActivity(listado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    public void funcionBoton(View v) {

        String name = nombre.getText().toString();
        String age = edad.getText().toString();
        String phone = celular.getText().toString();
        String insta = instagram.getText().toString();

                if(nombre.getText().toString().equals("") || edad.getText().toString().equals("") || celular.getText().toString().equals("") || instagram.getText().toString().equals("")){

                    Toast.makeText(this, "Campos vacio", Toast.LENGTH_SHORT).show();

                }else {
                    Invitado invitado = new Invitado();
                    invitado.setUid(UUID.randomUUID().toString());
                    invitado.setNombre(name);
                    invitado.setEdad(age);
                    invitado.setCelular(phone);
                    invitado.setInstagram(insta);
                    databaseReference.child("Invitado").child(invitado.getUid()).setValue(invitado);

                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }


    }

    private void limpiarCajas() {
        nombre.setText("");
        edad.setText("");
        celular.setText("");
        instagram.setText("");

    }

    private void validacion() {
        String name = nombre.getText().toString();

        if(nombre.equals("")){
            nombre.setError("Campo incompleto");
        }
    }
}