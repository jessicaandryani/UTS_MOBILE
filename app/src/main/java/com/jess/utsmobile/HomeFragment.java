package com.jess.utsmobile;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        masukText = view.findViewById(R.id.masuk);
        keluarText = view.findViewById(R.id.keluar);
        saldoText = view.findViewById(R.id.saldo);
        FloatingActionButton tambahButton = view.findViewById(R.id.tambah);


        tambahButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PilihanActivity.class);
            startActivity(intent);
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DatabaseHelper(getContext());
        transaksiList = db.getAllTransaksi();
        adapter = new TransaksiAdapter(transaksiList);
        adapter = new TransaksiAdapter(db.getAllTransaksi());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemDeleteListener(position -> {
            TransaksiModel transaksi = transaksiList.get(position);
            db.deleteTransaksi(transaksi.getId()); // Panggil dengan ID dari transaksi
            transaksiList.remove(position);
            adapter.notifyItemRemoved(position);

            // Perbarui tampilan total pengeluaran dan pemasukan
            updateTotal();

        });

        // Perbarui total setelah data dimuat
        updateTotal();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            updateTotal();
        }
    }


    private void updateTotal() {
        double totalMasuk = db.getTotalPemasukan();
        double totalKeluar = db.getTotalPengeluaran();
        double totalSaldo = db.getTotalSaldo();

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        masukText.setText(formatRupiah.format(totalMasuk));
        keluarText.setText(formatRupiah.format(totalKeluar));
        saldoText.setText(formatRupiah.format(totalSaldo));
    }
}
