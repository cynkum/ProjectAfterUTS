package com.example.aplikasikrs.Model;

public class KRS {
    private String kode;
    private String hari;
    private String sesi;
    private String jumlahSks;
    private String dosenPengampu;
    private String jmlMhs;

    public KRS(String kode, String hari, String sesi, String jumlahSks, String dosenPengampu, String jmlMhs) {
        this.kode = kode;
        this.hari = hari;
        this.sesi = sesi;
        this.jumlahSks = jumlahSks;
        this.dosenPengampu = dosenPengampu;
        this.jmlMhs = jmlMhs;

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
    public String getDosenPengampu() {
        return dosenPengampu;
    }
    public void setDosenPengampu(String dosenPengampu) {
        this.dosenPengampu = dosenPengampu;
    }
    public String getJmlMhs() {
        return jmlMhs;
    }
    public void setJmlMhs(String jmlMhs) {
        this.jmlMhs = jmlMhs;
    }


}
