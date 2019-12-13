package com.example.aplikasikrs.Model;

public class Matkul {
    private String kode;
    private String hari;
    private String sesi;
    private String jumlahSks;

    public Matkul(String kode, String hari, String sesi, String jumlahSks) {
        this.kode = kode;
        this.hari = hari;
        this.sesi = sesi;
        this.jumlahSks = jumlahSks;
    }

    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public String getHari() {
        return hari;
    }
    public void setHari(String hari) {
        this.hari = hari;
    }
    public String getSesi() {
        return sesi;
    }
    public void setSesi(String sesi) {
        this.sesi = sesi;
    }
    public String getJumlahSks() {
        return jumlahSks;
    }
    public void setJumlahSks(String jumlahSks) {
        this.jumlahSks = jumlahSks;
    }
}
