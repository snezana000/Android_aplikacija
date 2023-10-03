package com.example.seminarski;

public class Termin {
    private int id;
    private String nazivUsluge;
    private double cenaUsluge;
    private String datum; // New date field

    public Termin(int id, String nazivUsluge, double cenaUsluge, String datum) {
        this.id = id;
        this.nazivUsluge = nazivUsluge;
        this.cenaUsluge = cenaUsluge;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public double getCenaUsluge() {
        return cenaUsluge;
    }

    public String getDatum() {
        return datum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public void setCenaUsluge(double cenaUsluge) {
        this.cenaUsluge = cenaUsluge;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
