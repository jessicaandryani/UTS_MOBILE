package com.jess.utsmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
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
    private TextView masukText, keluarText;
    private DatabaseHelper db;
    private ArrayList<TransaksiModel> transaksiList;
    private TransaksiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize UI components
        recyclerView = view.findViewById(R.id.recyclerView);
        masukText = view.findViewById(R.id.masuk);
        keluarText = view.findViewById(R.id.keluar);
        FloatingActionButton tambahButton = view.findViewById(R.id.tambah);

        // Set click listener for FloatingActionButton
        tambahButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PilihanActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize database
        db = new DatabaseHelper(getContext());

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        transaksiList = db.getAllTransaksi();
        adapter = new TransaksiAdapter(transaksiList);
        recyclerView.setAdapter(adapter);

        // Update total pemasukan dan pengeluaran
        updateTotal();


    }

    // Method to update total pemasukan and pengeluaran
    private void updateTotal() {
        double totalMasuk = db.getTotalPemasukan();
        double totalKeluar = db.getTotalPengeluaran();

        // Format currency to Rupiah
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        formatRupiah.setMaximumFractionDigits(0); // Remove decimals

        // Set formatted totals to TextViews
        masukText.setText("Penghasilan: " + formatRupiah.format(totalMasuk));
        keluarText.setText("Pengeluaran: " + formatRupiah.format(totalKeluar));
    }
}
