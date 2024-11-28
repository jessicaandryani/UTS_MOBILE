package com.jess.utsmobile;

public class TransaksiModel {
    private String tanggal;
    private String kategori;
    private double jumlah;
    private String jenis; // Pemasukan atau Pengeluaran

    // Constructor
    public TransaksiModel(int anInt, String tanggal, String kategori, double jumlah, String jenis) {
        this.tanggal = tanggal;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.jenis = jenis;
    }

    // Getters
    public String getTanggal() {
        return tanggal;
    }

    public String getKategori() {
        return kategori;
    }

    public double getJumlah() {
        return jumlah;
    }

    public String getJenis() {
        return jenis;
    }

}
