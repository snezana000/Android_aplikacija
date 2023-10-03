package com.example.seminarski;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "seminarski.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_KORISNIK = "korisnik";
    private static final String TABLE_TERMINI = "termini";

    // Common column names
    private static final String COLUMN_ID = "id";

    // Table korisnik column names
    private static final String COLUMN_KORISNIK_IME = "ime";
    private static final String COLUMN_KORISNIK_SIFRA = "sifra";

    // Table termini column names
    private static final String COLUMN_TERMINI_USLUGA = "usluga";
    private static final String COLUMN_TERMINI_DATUM_VREME = "datum_vreme";
    private static final String COLUMN_TERMINI_CENA = "cena";
    private static final String COLUMN_TERMINI_KORISNIK_ID = "korisnik_id";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create korisnik table
        String CREATE_KORISNIK_TABLE = "CREATE TABLE " + TABLE_KORISNIK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_KORISNIK_IME + " TEXT,"
                + COLUMN_KORISNIK_SIFRA + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_KORISNIK_TABLE);

        // Create termini table
        String CREATE_TERMINI_TABLE = "CREATE TABLE " + TABLE_TERMINI + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TERMINI_USLUGA + " TEXT,"
                + COLUMN_TERMINI_DATUM_VREME + " TEXT,"
                + COLUMN_TERMINI_CENA + " REAL,"
                + COLUMN_TERMINI_KORISNIK_ID + " INTEGER,"
                + "FOREIGN KEY (" + COLUMN_TERMINI_KORISNIK_ID + ") REFERENCES " + TABLE_KORISNIK + "(" + COLUMN_ID + ")"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TERMINI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMINI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_KORISNIK);

        // Create new tables
        onCreate(sqLiteDatabase);
    }
    void dodajKorisnika (String korisnickoime, String sifra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_KORISNIK_IME, korisnickoime);
        cv.put(COLUMN_KORISNIK_SIFRA, sifra);
        long rezultat = db.insert(TABLE_KORISNIK, null,cv);

    }

    boolean login(String ime, String sifra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM korisnik WHERE sifra = " + "'" + sifra + "'" + " AND ime = " + "'" + ime + "'", null);
        if(c.getCount() == 0)
        {
            return false;
        }
        else {
            return true;
        }

    }
    String vratiID(String ime, String sifra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT id FROM korisnik WHERE sifra = " + "'" + sifra + "'" + " AND ime = " + "'" + ime + "'", null);
        c.moveToFirst();
        return c.getString(0);
    }
    public void noviTermin(int id, String usluga, float cena, String datum) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TERMINI_KORISNIK_ID, id);
        values.put(COLUMN_TERMINI_USLUGA, usluga);
        values.put(COLUMN_TERMINI_DATUM_VREME, datum);
        values.put(COLUMN_TERMINI_CENA, cena);

        db.insert(TABLE_TERMINI, null, values);

        db.close();
    }
    Cursor vrati_ime(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if(db != null){
            c = db.rawQuery("SELECT  ime, sifra FROM korisnik WHERE id= "+id , null);
        }
        return c;
    }
    void  obrisiKorisnika(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "DELETE FROM korisnik WHERE id = " + id;
        db.execSQL(command);
        command = "DELETE FROM termini WHERE korisnik_id = " + id;
        db.execSQL(command);
    }
    void upisiNovePodatke(String id, String ime, String sifra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "UPDATE Korisnik SET ime = " + "'" + ime + "'" + ", sifra = " +"'" + sifra + "'"  + " WHERE id = "   + id;
        db.execSQL(command);
    }

    Cursor vrati_termine(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if(db != null){
            c = db.rawQuery("SELECT id, usluga, datum_vreme, cena FROM termini WHERE korisnik_id= "+id+" order by 4 ASC" , null);
        }
        return c;
    }
    Cursor vrati_termine_opadajuci(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if(db != null){
            c = db.rawQuery("SELECT id, usluga, datum_vreme, cena FROM termini WHERE korisnik_id= "+id+" order by 4 DESC" , null);
        }
        return c;
    }
    void obrisiTermin (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "DELETE FROM termini WHERE id = " + id;
        db.execSQL(command);
    }
    Cursor pretraga(String id, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {

            String query = "SELECT id, usluga, datum_vreme, cena FROM termini WHERE korisnik_id = ? AND usluga LIKE ? ORDER BY 4 DESC";

            c = db.rawQuery(query, new String[]{id, s + "%"});


        }
        return c;
    }

}
