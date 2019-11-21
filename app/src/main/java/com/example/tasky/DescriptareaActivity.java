package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class DescriptareaActivity extends AppCompatActivity {
    public String dato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptarea);
        ListView lv1 = (ListView) findViewById(R.id.listaDescripcion);
        Bundle bundle = getIntent().getExtras();
        dato=bundle.getString("tareaSeleccionada");
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
