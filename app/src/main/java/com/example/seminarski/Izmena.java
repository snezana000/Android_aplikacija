package com.example.seminarski;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.seminarski.MainActivity.id;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Izmena extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena);
        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.setImageResource(R.drawable.pozadina);
        EditText Ime = findViewById(R.id.Ime);
        EditText Sifra = findViewById(R.id.Sifra1);
        EditText Potvrda = findViewById(R.id.Sifra2);
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor cursor = db.vrati_ime(Integer.parseInt(id));
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()){
            Ime.setText(cursor.getString(0));
            Sifra.setText(cursor.getString(1));
            Potvrda.setText(cursor.getString(1));
        }
    }
    public void Obrisi (View view) {
        DatabaseHelper baza = new DatabaseHelper(Izmena.this);
        baza.obrisiKorisnika(Integer.parseInt(id));
        Toast.makeText(this, "Korisnik je obrisan", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void Izmeni (View view) {
        EditText Ime, sifra1, sifra2;
        Ime=findViewById(R.id.Ime);
        sifra1=findViewById(R.id.Sifra1);
        sifra2=findViewById(R.id.Sifra2);
        String IME = Ime.getText().toString().trim();
        String SIFRA1 = sifra1.getText().toString().trim();
        String SIFRA2 = sifra2.getText().toString().trim();
        if(SIFRA1.equals(SIFRA2)){
            DatabaseHelper baza = new DatabaseHelper(Izmena.this);
            baza.upisiNovePodatke(id, IME, SIFRA1);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, Pocetna.class);
        startActivity(intent);
        this.finish();
    }
}