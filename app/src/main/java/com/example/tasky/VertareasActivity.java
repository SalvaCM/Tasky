package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VertareasActivity extends AppCompatActivity {
    private TextView tv1;
    private ListView lv1;
    private Tareas tareaSeleccionada;
    public ArrayList<Tareas> tareas;
    public ArrayAdapter<Tareas> adapter;
    int posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        tareas= new ArrayList<Tareas>();
        tareas = (ArrayList<Tareas>)intent.getSerializableExtra("tareas");


        for (int i =0;i<tareas.size();i++)
        {
            Log.println(Log.INFO, "Info", tareas.get(i).toString());
        }
        setContentView(R.layout.activity_vertarea);
        lv1 =(ListView)findViewById(R.id.listaTareas);
        adapter = new ArrayAdapter<Tareas>(this,android.R.layout.simple_list_item_1, tareas);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView adapterView, View view,
                                    int i, long l) {

                tareaSeleccionada = (Tareas)lv1.getItemAtPosition(i);
                posicion = i;
                ver(view);
            }

        });

        registerForContextMenu(lv1);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionmenuop, menu);
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        getMenuInflater().inflate(R.menu.menuopciones2, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.borrado:
                tareaSeleccionada = (Tareas)lv1.getItemAtPosition(posicion);
                borrar() ;
                break;
            case R.id.realizar:
                tareaSeleccionada = (Tareas)lv1.getItemAtPosition(posicion);
                realizar() ;
                break;

        }
        return true;
    }

    private void realizar() {
        AdminSQLite dbHelper = new AdminSQLite(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            ContentValues registro = new ContentValues();
            registro.put("realizada", 1);
            int cod = tareaSeleccionada.getCodigo();
            int mod = db.update("taskytareas",registro, "codigo=" + cod, null);
            db.close();
            if (mod == 1){
                Toast.makeText(this, "Tarea modificada",Toast.LENGTH_SHORT)
                        .show();
                 adapter.remove(tareaSeleccionada);
                 tareaSeleccionada.setRealizada(1);
                 adapter.add(tareaSeleccionada);
                 adapter.notifyDataSetChanged();
            }
            else
                Toast.makeText(this, "No se pudo modificar la tarea",Toast.LENGTH_SHORT).show();
        }

    }

        public void borrar() {
            AdminSQLite dbHelper = new AdminSQLite(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                int cod= tareaSeleccionada.getCodigo();
                int borrado = db.delete("taskytareas", "codigo=" + cod, null);
                db.close();
                if (borrado == 1) {

                    Toast.makeText(this, "Tarea Borrada",Toast.LENGTH_SHORT).show();
                    adapter.remove(tareaSeleccionada);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(this, "Error al borrar",Toast.LENGTH_SHORT).show();
                }

            } else {

                Toast.makeText(this,"Fallo al obtener la DB",Toast.LENGTH_SHORT).show();
            }
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
            
        }
        if (id==R.id.acercaDe) {
            Intent intent = new Intent( this,AcercadeActivity.class );
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void ver(View view) {
         Intent intent=new Intent(this ,DescriptareaActivity.class);
         intent.putExtra("tareaSeleccionada",tareaSeleccionada );
         startActivity(intent);
         finish();
    }

    public void aMenuTareas(View v) {
        Intent intent = new Intent( this, MenuActivity.class );
        startActivity(intent);
        finish();
    }



}
