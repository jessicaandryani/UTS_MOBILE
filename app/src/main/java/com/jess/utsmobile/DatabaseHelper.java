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

<<<<<<< HEAD
    private static final String DATABASE_NAME = "UTSMobile.db";
    private static final int DATABASE_VERSION = 2; // Tingkatkan versi jika struktur tabel diubah
    private static final String TABLE_USERS = "users";
=======
    private static final String DATABASE_NAME = "keuangan.db";
    private static final int DATABASE_VERSION = 2;
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457

    private static final String TABLE_NAME = "transaksi";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TANGGAL = "tanggal";
    private static final String COLUMN_KATEGORI = "kategori";
    private static final String COLUMN_JUMLAH = "jumlah";
    private static final String COLUMN_TIPE = "tipe";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
<<<<<<< HEAD
        // Membuat tabel users
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT UNIQUE, " +
                "nama TEXT, " +
                "password TEXT)");
=======
        db.execSQL("CREATE TABLE IF NOT EXISTS users (email TEXT PRIMARY KEY, password TEXT)");
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457

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
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

<<<<<<< HEAD
    // Menambahkan data user
    public Boolean insertData(String email, String nama, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
=======
    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("nama", nama);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        return result != -1;
    }


    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE email = ? AND password = ?", new String[]{email, password});
        boolean valid = cursor.getCount() > 0;
        cursor.close();
        return valid;
    }

<<<<<<< HEAD

    // Menambahkan data transaksi
=======
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
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
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_JUMLAH + ") FROM " + TABLE_NAME + " WHERE " + COLUMN_TIPE + " = 'Pemasukan'", null);

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();

        Log.d("DatabaseHelper", "Total Pemasukan: " + total);
        return total;
    }
    public String getNamaByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nama FROM " + TABLE_USERS + " WHERE email = ?", new String[]{email});
        String nama = null;

        if (cursor.moveToFirst()) {
            nama = cursor.getString(0); // Kolom pertama adalah nama
        }else {
            Log.d("DatabaseHelper", "Nama tidak ditemukan untuk email: " + email);
        }
        cursor.close();
        return nama;
    }

    public double getTotalPengeluaran() {
        double total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_JUMLAH + ") FROM " + TABLE_NAME + " WHERE " + COLUMN_TIPE + " = 'Pengeluaran'", null);
<<<<<<< HEAD

=======
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();

        Log.d("DatabaseHelper", "Total Pengeluaran: " + total);
        return total;
    }

<<<<<<< HEAD

    // Mengambil semua data transaksi
=======
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
    public ArrayList<TransaksiModel> getAllTransaksi() {
        ArrayList<TransaksiModel> transaksiList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                TransaksiModel transaksi = new TransaksiModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_JUMLAH)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPE))
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
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
<<<<<<< HEAD


    public double getTotalSaldo() {
        return getTotalPemasukan() - getTotalPengeluaran();
    }
}
=======
}
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
