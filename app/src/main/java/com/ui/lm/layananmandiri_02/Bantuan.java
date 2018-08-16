package com.ui.lm.layananmandiri_02;

import java.io.Serializable;

public class Bantuan implements Serializable {

    private String nama="";
    private String sdate="";
    private String edate ="";

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    @Override
    public String toString() {
        return "Bantuan{" +
                " nama='" + nama + '\'' +
                ", sdate='" + sdate + '\'' +
                ", edate='" + edate + '\'' +
                '}';
    }
}
