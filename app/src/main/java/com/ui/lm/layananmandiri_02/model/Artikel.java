package com.ui.lm.layananmandiri_02.model;

public class Artikel {
    private String judul;
    private String kategori;
    private String url;
    private Integer id;

    public Artikel() {
    }

    public Artikel(String judul, String kategori, String url, Integer id) {
        this.judul = judul;
        this.kategori = kategori;
        this.url = url;
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public String getKategori() {
        return kategori;
    }

    public String getUrl() {
        return url;
    }

    public Integer getId() {
        return id;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
