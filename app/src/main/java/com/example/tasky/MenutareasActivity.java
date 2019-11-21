package com.example.tasky;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MenutareasActivity extends AppCompatActivity {
    public ArrayList<String> tareas;
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
                "Tasky.sqlite", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select nombre from taskytareas", null);
        if (fila.moveToFirst()) {
            do {
                tareas.add(fila.getString(1));

            } while(fila.moveToNext());
        }
        bd.close();
    }
    public void abreDialogo(){

        AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.cambiarpw,null);
        mbuilder.setTitle(R.string.canbiarPass);
        Button cambiar = mView.findViewById(R.id.btnAceptarPW);
        Button cancelar = mView.findViewById(R.id.btnCancelarPW);

        final EditText nuevaPW = mView.findViewById(R.id.etNewpw);
        final EditText viejaPW = mView.findViewById(R.id.etOldpw);
        cambiar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences prefe=getSharedPreferences("user",
                        Context.MODE_PRIVATE);
                String passUserBD=prefe.getString("UserPass","");
                if (viejaPW.getText().toString()==passUserBD)
                {
                    SharedPreferences.Editor editor=prefe.edit();
                    editor.putString("UserPass", nuevaPW.getText().toString());
                    editor.commit();
                    //finish();
                    Toast.makeText(MenutareasActivity.this,"Contraseña Cambiada",
                            Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(MenutareasActivity.this,"Contraseña Incorrecta",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        cambiar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mbuilder.setView(mView);
        AlertDialog dialog = mbuilder.create();
        dialog.show();

    }

}
