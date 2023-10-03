package com.example.seminarski;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.seminarski.MainActivity.id;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class UnosTermina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_termina);
        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.setImageResource(R.drawable.pozadina);
    }
    public void Uneseno (View view){
        EditText Usluga=findViewById(R.id.Usluga);
        EditText Datum=findViewById(R.id.Datum);
        EditText Cena=findViewById(R.id.Cena);
        String USLUGA = Usluga.getText().toString();
        String DATUM = Datum.getText().toString();
        String CENA= Cena.getText().toString();
        float cena;
        try {
            cena = Float.parseFloat(CENA);
        } catch (NumberFormatException e) {
            // Greška prilikom parsiranja vrijednosti
            cena = 0.0f; // ili neka druga vrijednost koju želite postaviti kao zadani broj
        }
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.noviTermin(Integer.parseInt(id), USLUGA, cena, DATUM);
        Intent intent = new Intent(this, Pocetna.class);
        startActivity(intent);
        this.finish();
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, Pocetna.class);
        startActivity(intent);
        this.finish();
    }
}