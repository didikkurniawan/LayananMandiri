package com.ui.lm.layananmandiri_02;

import java.io.Serializable;

public class Layanan implements Serializable {

    private String NomorSurat="";
    private String JenisSurat="";
    private String NamaStaff ="";
    private String Tanggal="";
    private String desa="";

    public void setDesa(String desa){
        this.desa = desa;
    }
    public String getDesa(){
        return desa;
    }

    public String getNomorSurat() {
        return NomorSurat;
    }

    public void setNomorSurat(String NomorSurat) {
        this.NomorSurat = NomorSurat;
    }

    public String getNamaStaff() {
        return NamaStaff;
    }

    public void setNamaStaff(String NamaStaff) {
        this.NamaStaff = NamaStaff;
    }

    public String getJenisSurat() {
        return JenisSurat;
    }

    public void setJenisSurat(String JenisSurat) {
        this.JenisSurat = JenisSurat;
    }

    public void setTanggal (String Tanggal) {this.Tanggal = Tanggal; }

    public String getTanggal() {return Tanggal; }

    @Override
    public String toString() {
        return "Layanan{" +
                " NomorSurat='" + NomorSurat + '\'' +
                ",JenisSurat+'" + JenisSurat + '\'' +
                ", NamaStaff='" + NamaStaff + '\'' +
                ", Tanggal='" + Tanggal + '\'' +
                '}';
    }
}
