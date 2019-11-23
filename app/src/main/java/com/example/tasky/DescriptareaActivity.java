package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DescriptareaActivity extends AppCompatActivity {
    public Tareas tareaSeleccionada;
    public EditText etNombre;
    public EditText etDescripcion;
    public EditText etPrioridad;
    public EditText etFecha;
    public EditText etCoste;
    public CheckBox cboxRealizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tareaSeleccionada = new Tareas();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptarea);
        Intent intent = getIntent();
        tareaSeleccionada = (Tareas) intent.getSerializableExtra("tareaSeleccionada");

        etNombre = findViewById(R.id.nombre2);
        etDescripcion = findViewById(R.id.descripcion2);
        etPrioridad = findViewById(R.id.prioridad2);
        etFecha = findViewById(R.id.fecha2);
        etCoste = findViewById(R.id.coste2);
        cboxRealizada = findViewById(R.id.cboxRealizada);

        etNombre.setText(tareaSeleccionada.getNombre());
        etDescripcion.setText(tareaSeleccionada.getDescripcion());
        etPrioridad.setText(tareaSeleccionada.getPrioridad());
        etFecha.setText(tareaSeleccionada.getFecha());
        etCoste.setText(String.valueOf(tareaSeleccionada.getPrecio()));
        if (tareaSeleccionada.getRealizada() == 0) {
            cboxRealizada.setChecked(false);
        } else {
            cboxRealizada.setChecked(true);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nuevaTarea) {
            Intent intent = new Intent(this, CreartareaActivity.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.cambiarPass) {
            //TODO
        }
        if (id == R.id.acercaDe) {
            Intent intent = new Intent(this, AcercadeActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void borrarTarea(View view) {
        AdminSQLite dbHelper = new AdminSQLite(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            
        } else {
            Toast.makeText(this,"Fallo al obtener la DB",Toast.LENGTH_SHORT).show();
        }
    }
}