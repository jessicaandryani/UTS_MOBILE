package com.jess.utsmobile;

public class TransaksiModel {
    private int id;
    private String tanggal;
    private String kategori;
    private double jumlah;
    private String jenis; // Pemasukan atau Pengeluaran

<<<<<<< HEAD
    public TransaksiModel(int id,String tanggal, String kategori, double jumlah, String jenis) {
=======
    // Constructor
    public TransaksiModel(int id, String tanggal, String kategori, double jumlah, String jenis) {
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
        this.id = id;
        this.tanggal = tanggal;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.jenis = jenis;
    }
<<<<<<< HEAD
    public int getId() {
        return id;
    }
=======

    // Getters
    public int getId() {
        return id;
    }

>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
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
<<<<<<< HEAD
}
=======
}
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
