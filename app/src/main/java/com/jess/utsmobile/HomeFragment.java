package com.jess.utsmobile;

<<<<<<< HEAD
import static android.app.Activity.RESULT_OK;

=======
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView masukText, keluarText, saldoText;
    private DatabaseHelper db;
    private ArrayList<TransaksiModel> transaksiList;
    private TransaksiAdapter adapter;
    private static final int REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        masukText = view.findViewById(R.id.masuk);
        keluarText = view.findViewById(R.id.keluar);
        saldoText = view.findViewById(R.id.saldo);
        FloatingActionButton tambahButton = view.findViewById(R.id.tambah);

<<<<<<< HEAD

=======
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
        tambahButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PilihanActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DatabaseHelper(getContext());
<<<<<<< HEAD
=======

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
        transaksiList = db.getAllTransaksi();
        adapter = new TransaksiAdapter(transaksiList);
        adapter = new TransaksiAdapter(db.getAllTransaksi());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemDeleteListener(position -> {
            TransaksiModel transaksi = transaksiList.get(position);
<<<<<<< HEAD
            db.deleteTransaksi(transaksi.getId()); // Panggil dengan ID dari transaksi
            transaksiList.remove(position);
            adapter.notifyItemRemoved(position);

            // Perbarui tampilan total pengeluaran dan pemasukan
            updateTotal();

        });

        // Perbarui total setelah data dimuat
=======
            db.deleteTransaksi(transaksi.getId());
            transaksiList.remove(position);
            adapter.notifyItemRemoved(position);
            updateTotal();
        });

        updateTotal();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            String tanggal = data.getStringExtra("tanggal");
            String kategori = data.getStringExtra("kategori");
            double jumlah = data.getDoubleExtra("jumlah", 0);

            addNewTransaksi(new TransaksiModel(0, tanggal, kategori, jumlah, "Pemasukan"));
        }
    }

    public void addNewTransaksi(TransaksiModel transaksi) {
        transaksiList.add(0, transaksi);
        adapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
        updateTotal();
    }

<<<<<<< HEAD

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            updateTotal();
        }
    }


=======
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
    private void updateTotal() {
        double totalMasuk = db.getTotalPemasukan();
        double totalKeluar = db.getTotalPengeluaran();
        double totalSaldo = db.getTotalSaldo();

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
<<<<<<< HEAD
        masukText.setText(formatRupiah.format(totalMasuk));
        keluarText.setText(formatRupiah.format(totalKeluar));
        saldoText.setText(formatRupiah.format(totalSaldo));
=======
        formatRupiah.setMaximumFractionDigits(0);

        masukText.setText("Penghasilan: " + formatRupiah.format(totalMasuk));
        keluarText.setText("Pengeluaran: " + formatRupiah.format(totalKeluar));
>>>>>>> b511c098b8c6a82cdb66c355191deafce9694457
    }
}