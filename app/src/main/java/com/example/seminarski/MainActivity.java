package com.example.seminarski;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.seminarski.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.setImageResource(R.drawable.pozadina);

    }
    public void Registracija (View view) {
        Intent intent = new Intent(this, Registracija.class);
        startActivity(intent);
        this.finish();
    }
    public void Prijava (View view) {
        DatabaseHelper baza = new DatabaseHelper(MainActivity.this);
        EditText ime = findViewById(R.id.ime);
        EditText sifra = findViewById(R.id.sifra);
        String i = ime.getText().toString().trim();
        String s = sifra.getText().toString().trim();
        if (baza.login(i, s)) {
            id=baza.vratiID(i, s);
            Intent intent = new Intent(this, Pocetna.class);
            startActivity(intent);
            this.finish();
        }
    }
}