package com.example.organizadorfiestas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, edad, celular, instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.txtNombre);
        edad = findViewById(R.id.txtEdad);
        celular = findViewById(R.id.txtCelular);
        instagram = findViewById(R.id.txtInstagram);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String name = nombre.getText().toString();

        switch (item.getItemId()){
            case R.id.btnAgregar:{
                if(nombre.equals("")){
                    validacion();
                }else {
                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                    break;
                }

            }
        }
        return true;
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