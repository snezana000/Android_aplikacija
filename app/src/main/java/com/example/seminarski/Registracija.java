package com.example.seminarski;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Registracija extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.setImageResource(R.drawable.pozadina);

    }

    public void Dodajkorisnika (View view) {
        EditText ime = findViewById(R.id.editTextUsername);
        EditText sifra = findViewById(R.id.editTextPassword);
        EditText potvrda = findViewById(R.id.editTextConfirmPassword);
        String Ime = ime.getText().toString().trim();
        String SIFRA1 = sifra.getText().toString().trim();
        String SIFRA2 = potvrda.getText().toString().trim();
        if(SIFRA1.equals(SIFRA2)){
            DatabaseHelper baza = new DatabaseHelper(Registracija.this);
            baza.dodajKorisnika(Ime, SIFRA1);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        else {Toast.makeText(this, "Sifra nije dobro uneta", Toast.LENGTH_SHORT).show();
            sifra.getText().clear();
            potvrda.getText().clear();}
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    }
