package com.example.appperros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irIniciar(View View){
        Intent i = new Intent(this, IniciarSesionActivity.class);
        startActivity(i);
    }

    public void irRegistrarse(View View){
        Intent i = new Intent(this, Registrarse.class);
        startActivity(i);

    }




}