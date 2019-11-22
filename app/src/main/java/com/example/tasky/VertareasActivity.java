package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class VertareasActivity extends AppCompatActivity {
    private TextView tv1;
    private ListView lv1;
    private String tareaSeleccionada;
    public ArrayList<String> tareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        tareas=bundle.getStringArrayList("tareas");

        for (int i =0;i<tareas.size();i++)
        {
            System.out.println("Tareas");
            System.out.println(tareas.get(i).toString());
        }
        setContentView(R.layout.activity_vertarea);
        lv1 =(ListView)findViewById(R.id.listaTareas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, tareas);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView adapterView, View view,
                                    int i, long l) {

                tareaSeleccionada = lv1.getItemAtPosition(i).toString();
                ver(view);
            }
        });
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





}
