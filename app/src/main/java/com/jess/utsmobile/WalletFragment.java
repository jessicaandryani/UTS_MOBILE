package com.jess.utsmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class WalletFragment extends Fragment {

    private TextView cleanAssetsValue; // Untuk menampilkan Aset Bersih
    private TextView accountBalance;   // Untuk menampilkan saldo akun
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        // Inisialisasi UI elements
        cleanAssetsValue = view.findViewById(R.id.clean_assets_value);   // Menemukan clean_assets_value TextView
        accountBalance = view.findViewById(R.id.account_balance);        // Menemukan account_balance TextView

        // Inisialisasi DatabaseHelper
        dbHelper = new DatabaseHelper(getContext());

        // Mengambil dan menampilkan total pemasukan (Aset Bersih)
        displayAsetBersih();

        return view;
    }

    // Fungsi untuk menampilkan Aset Bersih
    private void displayAsetBersih() {
        // Mengambil total pemasukan dan total pengeluaran dari database
        double totalPemasukan = dbHelper.getTotalPemasukan();
        double totalPengeluaran = dbHelper.getTotalPengeluaran();

        // Menghitung Aset Bersih (total pemasukan - total pengeluaran)
        double asetBersih = totalPemasukan - totalPengeluaran;

        // Menampilkan Aset Bersih di UI
        cleanAssetsValue.setText("Rp " + String.format("%,.0f", asetBersih));

        // Menampilkan saldo akun (misalnya saldo = pemasukan - pengeluaran)
        double saldoAkun = totalPemasukan - totalPengeluaran;
        accountBalance.setText("Rp " + String.format("%,.0f", saldoAkun));
    }
}
