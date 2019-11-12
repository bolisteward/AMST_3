package com.example.grupo4_ta3_iniciosesionfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    DatabaseReference db_reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Registros");
        leerRegistros();
    }
    public void leerRegistros(){}
}
