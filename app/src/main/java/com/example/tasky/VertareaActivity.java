package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class VertareaActivity extends AppCompatActivity {
    private String[] tareas={"comprar el pan","fregar los platos","comprar un portatil"};
    private TextView tv1;
    private ListView lv1;
    private String tareaSeleccionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertarea);
        lv1 =(ListView)findViewById(R.id.listaTareas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, tareas);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView adapterView, View view,
                                    int i, long l) {

                tareaSeleccionada = lv1.getItemAtPosition(i).toString();
                
            }
        });

    }
    public void ver(View view) {
        Intent i=new Intent(this,DescriptareaActivity.class);
        i.putExtra("tareas", tareaSeleccionada);
        startActivity(i);
        //finish();
    }

}
