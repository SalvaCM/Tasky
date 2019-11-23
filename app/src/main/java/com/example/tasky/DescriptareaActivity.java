package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DescriptareaActivity extends AppCompatActivity {
    public Tareas tareaSeleccionada ;
    public EditText etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tareaSeleccionada = new Tareas();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptarea);
        Bundle bundle = getIntent().getExtras();
        tareaSeleccionada.setNombre(bundle.getString("tareaSeleccionada"));
        etNombre = findViewById(R.id.nombre2);
        etNombre.setText(tareaSeleccionada.nombre);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.nuevaTarea) {
            Intent intent = new Intent( this,CreartareaActivity.class );
            startActivity(intent);
            finish();
        }
        if (id==R.id.cambiarPass) {
           //TODO
        }
        if (id==R.id.acercaDe) {
            Intent intent = new Intent( this,AcercadeActivity.class );
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
