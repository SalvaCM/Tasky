package com.example.tasky;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MenutareasActivity extends AppCompatActivity {
    public ArrayList<Tareas> tareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menutareas);
    }
    @Override
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
            abreDialogo();
        }
        if (id==R.id.acercaDe) {
            Intent intent = new Intent( this,AcercadeActivity.class );
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void aCrearTarea(View v) {
        Intent intent = new Intent( this,CreartareaActivity.class );
        startActivity(intent);
        finish();
    }
    public void aVerTareas(View v) {
        cargarTareas(v);
        Intent intent = new Intent( this,VertareasActivity.class );
        intent.putExtra("tareas",tareas);
        startActivity(intent);
        finish();
    }
    public void cargarTareas(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "taskybd", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        tareas = new ArrayList<Tareas>();
        Cursor fila = bd.rawQuery("SELECT * from taskytareas", null);
        if (fila.moveToFirst()) {
            do {
                Tareas tarea = new Tareas();
                tarea.setCodigo(fila.getInt(0));
                tarea.setNombre(fila.getString(1));
                tarea.setDescripcion(fila.getString(2));
                tarea.setPrioridad(fila.getString(3));
                tarea.setFecha(fila.getString(4));
                String descripcion = fila.getString(2);
                String prioridad = fila.getString(3);
                String fecha = fila.getString(4);
                double coste = fila.getDouble(5);
                tareas.add(tarea);
                


            } while(fila.moveToNext());
        }
        //Log.println(Log.INFO,"Info", tareas.get(0).toString());


        fila.close();
        bd.close();
    }
    public void abreDialogo(){

        AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.cambiarpw,null);
        mbuilder.setTitle(R.string.canbiarPass);
        Button cambiar = mView.findViewById(R.id.btnAceptarPW);
        Button cancelar = mView.findViewById(R.id.btnCancelarPW);

        final EditText nuevaPW = mView.findViewById(R.id.etNuevapw);
        final EditText viejaPW = mView.findViewById(R.id.etOldpw);
        mbuilder.setView(mView);
        final AlertDialog dialog = mbuilder.create();
        cambiar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cambiarPW(v, nuevaPW,viejaPW))
                {
                    dialog.dismiss();
                }

            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    private boolean cambiarPW(View mView,EditText nuevaPW,EditText viejaPW) {
        String vieja = viejaPW.getText().toString();
        String nueva = nuevaPW.getText().toString();
        SharedPreferences prefe=getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String passUserBD=prefe.getString("UserPass","");
        //Log.println(Log.INFO,"Info", "passVieja"+vieja);
        // Log.println(Log.INFO,"Info", "passNueva"+nueva);
        // Log.println(Log.INFO,"Info", "passUser"+passUserBD);
        if (vieja.equals(passUserBD))
        {
            SharedPreferences.Editor editor=prefe.edit();
            editor.putString("UserPass", nueva);
            editor.commit();
            Toast.makeText(this,"Contraseña Cambiada",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            Toast.makeText(this,"Contraseña Incorrecta",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
