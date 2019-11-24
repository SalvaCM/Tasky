package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreartareaActivity extends AppCompatActivity {
    private EditText etNombre,etDescripcion,etPrioridad,etFecha,etCoste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creartarea);

        etNombre=(EditText)findViewById(R.id.nombre);
        etDescripcion=(EditText)findViewById(R.id.descripcion);
        etPrioridad=(EditText)findViewById(R.id.prioridad);
        etFecha=(EditText)findViewById(R.id.fecha);
        etCoste=(EditText)findViewById(R.id.coste);
    }
    public void nuevaTarea(View view) {
        AdminSQLite dbHelper = new AdminSQLite(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            String nombre = etNombre.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            String prioridad = etPrioridad.getText().toString();
            String fecha = etFecha.getText().toString();
            Double precio = Double.parseDouble(etCoste.getText().toString());
            int realizada = 0;

            String query = "INSERT INTO taskytareas (nombre,descripcion,prioridad,fecha,precio,realizada)VALUES ('" + nombre + "', '" + descripcion + "','" + prioridad + "','" + fecha + "','" + precio + "','" + realizada + "')";
            db.execSQL(query);
            db.close();
            Toast.makeText(this, "Tarea Creada", Toast.LENGTH_SHORT).show();
            etNombre.setText("");
            etDescripcion.setText("");
            etPrioridad.setText("");
            etFecha.setText("");
            etCoste.setText("");

        } else {
            Toast.makeText(this, "Fallo al obtener la DB", Toast.LENGTH_SHORT).show();
        }
    }
    public void aMenuTareas(View v) {
        Intent intent = new Intent( this, MenuActivity.class );
        startActivity(intent);
        finish();
    }
    }


