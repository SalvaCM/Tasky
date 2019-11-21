package com.example.tasky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.usuario);
        et2=(EditText)findViewById(R.id.userpass);
        SharedPreferences preferencias=getSharedPreferences("user",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("Nombre", "Salva");
        editor.putString("UserPass", "admin");
        editor.commit();
    }
    public void logear(View v) {
        String nombre=et1.getText().toString();
        String pass=et2.getText().toString();
        SharedPreferences prefe=getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String nombreenBD=prefe.getString("Nombre","");
        String passUserBD=prefe.getString("UserPass","");
        if (nombreenBD.length()==0) {
            Toast.makeText(this,nombreenBD,
                    Toast.LENGTH_LONG).show();
        }
        else {
            if(nombre.equals(nombreenBD) && pass.equals(passUserBD))
            {
                Toast.makeText(this,"Usuario Correcto",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MenutareasActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(this,"Usuario incorrecto",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}