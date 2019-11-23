package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    public Button btnGuardar;
    public Button btnBorrar;
    public Button btnModificar;
    public Button btnCancelar;

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
        btnBorrar = findViewById(R.id.btnBorrar);
        btnGuardar = findViewById(R.id.btnGuadar);
        btnModificar = findViewById(R.id.btnModificar);
        btnCancelar = findViewById(R.id.btnCancelar);

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
                int cod= tareaSeleccionada.getCodigo();
                int borrado = db.delete("taskytareas", "codigo=" + cod, null);
                db.close();
                if (borrado == 1) {
                    Toast.makeText(this, "Tarea Borrada",
                            Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etDescripcion.setText("");
                    etPrioridad.setText("");
                    etFecha.setText("");
                    etCoste.setText("");
                    cboxRealizada.setChecked(false);
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Error al borrar",
                            Toast.LENGTH_SHORT).show();
                }

        } else {

            Toast.makeText(this,"Fallo al obtener la DB",Toast.LENGTH_SHORT).show();
        }
    }
    public void modTarea(View view) {
        btnBorrar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setVisibility(View.VISIBLE);
        btnGuardar.setVisibility(View.VISIBLE);
        etNombre.setEnabled(true);
        etDescripcion.setEnabled(true);
        etFecha.setEnabled(true);
        etPrioridad.setEnabled(true);
        etFecha.setEnabled(true);
        etCoste.setEnabled(true);
        cboxRealizada.setEnabled(true);

    }
    public void CancelarMod(View view) {
        btnBorrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnCancelar.setVisibility(View.INVISIBLE);
        btnGuardar.setVisibility(View.INVISIBLE);
        etNombre.setText(tareaSeleccionada.getNombre());
        etDescripcion.setText(tareaSeleccionada.getDescripcion());
        etPrioridad.setText(tareaSeleccionada.getPrioridad());
        etFecha.setText(tareaSeleccionada.getFecha());
        etCoste.setText(String.valueOf(tareaSeleccionada.getPrecio()));
        etNombre.setEnabled(false);
        etDescripcion.setEnabled(false);
        etFecha.setEnabled(false);
        etPrioridad.setEnabled(false);
        etFecha.setEnabled(false);
        etCoste.setEnabled(false);
        cboxRealizada.setEnabled(false);
        if (tareaSeleccionada.getRealizada() == 0) {
            cboxRealizada.setChecked(false);
        } else {
            cboxRealizada.setChecked(true);
        }

    }
    public void GuardarMod(View view) {

        int realizada;
        btnBorrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnCancelar.setVisibility(View.INVISIBLE);
        btnGuardar.setVisibility(View.INVISIBLE);
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String prioridad = etPrioridad.getText().toString();
        String fecha = etFecha.getText().toString();
        Double precio = Double.parseDouble(etCoste.getText().toString());
        etNombre.setEnabled(false);
        etDescripcion.setEnabled(false);
        etFecha.setEnabled(false);
        etPrioridad.setEnabled(false);
        etFecha.setEnabled(false);
        etCoste.setEnabled(false);
        cboxRealizada.setEnabled(false);
        if (cboxRealizada.isChecked()) {
            realizada = 1;
        } else {
            realizada = 0;
        }
        AdminSQLite dbHelper = new AdminSQLite(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {


            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("descripcion", descripcion);
            registro.put("prioridad", prioridad);
            registro.put("fecha", fecha);
            registro.put("precio", precio);
            registro.put("realizada", realizada);
            int cod = tareaSeleccionada.getCodigo();
            int mod = db.update("taskytareas",registro, "codigo=" + cod, null);
            db.close();
            if (mod == 1)
                Toast.makeText(this, "Tarea modificada",
                        Toast.LENGTH_SHORT)
                        .show();
            else
                Toast.makeText(this, "No se pudo modificar la tarea",
                        Toast.LENGTH_SHORT).show();
        }

    }
}