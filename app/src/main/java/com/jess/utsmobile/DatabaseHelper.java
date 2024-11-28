package com.jess.utsmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "keuangan.db"; // Gunakan satu nama konsisten
    private static final int DATABASE_VERSION = 2; // Naikkan versi jika ada perubahan

    // Nama tabel
    private static final String TABLE_NAME = "transaksi";

    // Kolom tabel
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TANGGAL = "tanggal";
    private static final String COLUMN_KATEGORI = "kategori";
    private static final String COLUMN_JUMLAH = "jumlah";
    private static final String COLUMN_TIPE = "tipe";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // Gunakan nama konsisten
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel users
        db.execSQL("CREATE TABLE IF NOT EXISTS users (email TEXT PRIMARY KEY, password TEXT)");

        // Membuat tabel transaksi
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TANGGAL + " TEXT, " +
                COLUMN_KATEGORI + " TEXT, " +
                COLUMN_JUMLAH + " REAL, " +
                COLUMN_TIPE + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hapus tabel lama
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Buat ulang tabel
        onCreate(db);
    }

    // Menambahkan data user
    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    // Menambahkan data transaksi
    public void addTransaksi(String tanggal, String kategori, double jumlah, String tipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TANGGAL, tanggal);
        values.put(COLUMN_KATEGORI, kategori);
        values.put(COLUMN_JUMLAH, jumlah);
        values.put(COLUMN_TIPE, tipe);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public double getTotalPemasukan() {
        double total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_JUMLAH + ") FROM " + TABLE_NAME + " WHERE " + COLUMN_TIPE + " = 'masuk'", null);

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();

        // Tambahkan log untuk debugging
        Log.d("DatabaseHelper", "Total Pemasukan: " + total);
        return total;
    }

    public double getTotalPengeluaran() {
        double total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_JUMLAH + ") FROM " + TABLE_NAME + " WHERE " + COLUMN_TIPE + " = 'keluar'", null);

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();

        // Tambahkan log untuk debugging
        Log.d("DatabaseHelper", "Total Pengeluaran: " + total);
        return total;
    }

    // Mengambil semua data transaksi
    public ArrayList<TransaksiModel> getAllTransaksi() {
        ArrayList<TransaksiModel> transaksiList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                TransaksiModel transaksi = new TransaksiModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4)
                );
                transaksiList.add(transaksi);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return transaksiList;
    }
    public void deleteTransaksi(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("transaksi", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}
