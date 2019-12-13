package com.example.aplikasikrs.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Mahasiswa {
    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("nim")
    @Expose
    private String nim;

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

    public Mahasiswa(String nama, String email, String alamat, String foto) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.foto = foto;

    }

    public Mahasiswa(String id, String nim, String nama, String email, String alamat, String foto) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
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
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
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
