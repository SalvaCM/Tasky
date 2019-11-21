package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

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
    public void nuevaTarea(View view){
        AdminSQLiteOpenHelper dbHelper = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            String nombre = etNombre.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            String prioridad = etPrioridad.getText().toString();
            String fecha = etFecha.getText().toString();
            Double precio = Double.parseDouble(etCoste.getText().toString());
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("descripcion",descripcion);
            registro.put("prioridad",prioridad);
            registro.put("fecha",fecha);
            registro.put("precio",nombre);
            db.insert("taskytareas", null, registro);
            db.close();
            etNombre.setText("");
            etDescripcion.setText("");
            etPrioridad.setText("");
            etFecha.setText("");
            etCoste.setText("");
        }
        else
            Toast.makeText(this,"Fallo al obtener la DB",Toast.LENGTH_LONG).show();
        }

    }


