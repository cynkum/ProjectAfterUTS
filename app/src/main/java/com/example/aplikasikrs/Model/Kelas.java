package com.example.aplikasikrs.Model;

public class Kelas {
    private String kode;
    private String hari;
    private String sesi;
    private String dosPengampu;
    private String jmlMhs;

    public Kelas(String kode, String hari, String sesi, String dosPengampu, String jmlMhs) {
        this.kode = kode;
        this.hari = hari;
        this.sesi = sesi;
        this.dosPengampu = dosPengampu;
        this.jmlMhs = jmlMhs;
    }
    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public String getDosPengampu() {
        return dosPengampu;
    }
    public void setDosPengampu(String dosPengampu) {
        this.dosPengampu = dosPengampu;
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
    public String getJmlMhs() {
        return jmlMhs;
    }
    public void setJmlMhs(String jmlMhs) {
        this.jmlMhs = jmlMhs;
    }

}
