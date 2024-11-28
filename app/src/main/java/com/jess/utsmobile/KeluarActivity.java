package com.jess.utsmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KeluarActivity extends AppCompatActivity {

    private EditText etTanggal, etKategori, etJumlah;
    private Button btnSave;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);

        etTanggal = findViewById(R.id.ettanggal);
        etKategori = findViewById(R.id.etkategori);
        etJumlah = findViewById(R.id.etjumlah);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);
        setCurrentDate();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = etTanggal.getText().toString();
                String kategori = etKategori.getText().toString();
                String jumlahStr = etJumlah.getText().toString();

                if (!tanggal.isEmpty() && !kategori.isEmpty() && !jumlahStr.isEmpty()) {
                    double jumlah = Double.parseDouble(jumlahStr);
                    db.addTransaksi(tanggal, kategori, jumlah, "Pengeluaran");
                    Toast.makeText(KeluarActivity.this, "Pengeluaran disimpan!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(KeluarActivity.this, "Isi semua field!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setCurrentDate() {
        // Mendapatkan tanggal saat ini
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(calendar.getTime());

        // Menampilkan tanggal saat ini di EditText
        etTanggal.setText(currentDate);
    }
}