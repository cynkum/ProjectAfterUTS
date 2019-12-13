package com.example.aplikasikrs.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dosen {

    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("nidn")
    @Expose
    private String nidn;

    @SerializedName("gelar")
    @Expose
    private String gelar;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("foto")
    @Expose
    private String foto;

    public Dosen(String nama, String gelar, String email, String alamat, String foto) {
        this.nama = nama;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
        this.foto = foto;
    }

    public Dosen(String id, String nidn, String nama, String gelar, String email, String alamat, String foto) {
        this.id = id;
        this.nidn = nidn;
        this.nama = nama;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
        this.foto = foto;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNidn() {
        return nidn;
    }
    public void setNidn(String nidn) {
        this.nidn = nidn;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getGelar() {
        return gelar;
    }
    public void setGelar(String gelar) {
        this.gelar = gelar;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getFoto(){
        return foto;
    }
    public void setFoto(int imageResource){
        this.foto = foto;
    }

}


