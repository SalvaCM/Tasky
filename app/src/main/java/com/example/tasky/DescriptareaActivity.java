package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class DescriptareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptarea);
        ListView lv1 = (ListView) findViewById(R.id.listaDescripcion);
        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("tarea");
    }
}
