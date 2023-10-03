package com.example.seminarski;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.seminarski.MainActivity.id;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Pocetna extends AppCompatActivity {
    RecyclerView recyclerView;
    TerminAdapter adapter;
     EditText etSearchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);
        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.setImageResource(R.drawable.pozadina);
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor cursor = db.vrati_termine(Integer.parseInt(id));
        recyclerView = findViewById(R.id.recyclerView);


        List<Termin> listaTermina = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int terminId = cursor.getInt(0);
                String nazivUsluge = cursor.getString(1);
                double cenaUsluge = cursor.getDouble(3);
                String datum = cursor.getString(2);

                Termin termin = new Termin(terminId, nazivUsluge, cenaUsluge, datum);
                listaTermina.add(termin);
            } while (cursor.moveToNext());

            cursor.close();
        }
        adapter = new TerminAdapter(listaTermina);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Pocetna.this));
        etSearchBox = findViewById(R.id.pretraga);
        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed.
                // We don't need to do anything here.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String searchQuery = charSequence.toString().trim();
                DatabaseHelper db = new DatabaseHelper(Pocetna.this);
                Cursor c = db.pretraga(id, searchQuery);
                recyclerView = findViewById(R.id.recyclerView);
                List<Termin> listaTermina = new ArrayList<>();

                if (c != null && c.moveToFirst()) {
                    do {
                        int terminId = c.getInt(0);
                        String nazivUsluge = c.getString(1);
                        double cenaUsluge = c.getDouble(3);
                        String datum = c.getString(2);

                        Termin termin = new Termin(terminId, nazivUsluge, cenaUsluge, datum);
                        listaTermina.add(termin);
                    } while (c.moveToNext());

                    c.close();
                }
                adapter = new TerminAdapter(listaTermina);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Pocetna.this));

            }



            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called after the text has been changed.
                // We don't need to do anything here.
            }
        });


        // Now you have the listaTermina with Termin objects from the Cursor data
        // Use it as needed for further processing or with your RecyclerView adapter
    }

    public void TerminiUnos (View view) {
        Intent intent = new Intent(this, UnosTermina.class);
        startActivity(intent);
        this.finish();
    }
    public void info(View view) {
        Intent intent = new Intent(this, Izmena.class);
        startActivity(intent);
        this.finish();
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void filter(View view){
        Button dugme = findViewById(R.id.filter);
        String tekst = dugme.getText().toString().trim();
        if(tekst.equals("Opadajuci")){
            dugme.setText("Rastuci");
            recyclerView = findViewById(R.id.recyclerView);
            DatabaseHelper db = new DatabaseHelper(Pocetna.this);

            Cursor c = db.vrati_termine_opadajuci(Integer.parseInt(id));
            List<Termin> listaTermina = new ArrayList<>();

            if (c != null && c.moveToFirst()) {
                do {
                    int terminId = c.getInt(0);
                    String nazivUsluge = c.getString(1);
                    double cenaUsluge = c.getDouble(3);
                    String datum = c.getString(2);

                    Termin termin = new Termin(terminId, nazivUsluge, cenaUsluge, datum);
                    listaTermina.add(termin);
                } while (c.moveToNext());
            }
            adapter = new TerminAdapter(listaTermina);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Pocetna.this));


        }else {
            dugme.setText("Opadajuci");
            recyclerView = findViewById(R.id.recyclerView);
            DatabaseHelper db = new DatabaseHelper(Pocetna.this);

            Cursor c = db.vrati_termine(Integer.parseInt(id));
            List<Termin> listaTermina = new ArrayList<>();

            if (c != null && c.moveToFirst()) {
                do {
                    int terminId = c.getInt(0);
                    String nazivUsluge = c.getString(1);
                    double cenaUsluge = c.getDouble(3);
                    String datum = c.getString(2);

                    Termin termin = new Termin(terminId, nazivUsluge, cenaUsluge, datum);
                    listaTermina.add(termin);
                } while (c.moveToNext());
            }
            adapter = new TerminAdapter(listaTermina);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Pocetna.this));
        }
    }
}