package com.jess.utsmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {

    private ArrayList<TransaksiModel> transaksiList;

    public TransaksiAdapter(ArrayList<TransaksiModel> transaksiList) {
        this.transaksiList = transaksiList;
    }
    // Tambahkan interface untuk klik item
    public interface OnItemClickListener {
        void onItemClick(int position); // Untuk menangani klik item
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransaksiModel transaksi = transaksiList.get(position);

        // Set tanggal transaksi
        holder.tanggal.setText(transaksi.getTanggal());

        // Set kategori transaksi
        holder.kategori.setText(transaksi.getKategori());

        // Gunakan formatJumlah untuk menambahkan tanda + atau - sesuai jenis transaksi
        String jumlahFormatted = formatJumlah(transaksi.getJumlah(), transaksi.getJenis());
        holder.jumlah.setText(jumlahFormatted);
        holder.itemView.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onItemDelete(position);
            }
        });
        holder.itemView.setOnClickListener(v -> {
            new android.app.AlertDialog.Builder(v.getContext()) // Gunakan konteks dari View
                    .setTitle("Hapus Transaksi")
                    .setMessage("Apakah Anda yakin ingin menghapus transaksi ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        // Hapus item jika pengguna memilih "Ya"
                        if (deleteListener != null) {
                            deleteListener.onItemDelete(position);
                        }
                    })
                    .setNegativeButton("Tidak", null) // Tutup dialog jika "Tidak" dipilih
                    .show();
        });
    }

    private String formatJumlah(double jumlah, String jenisTransaksi) {
        // Format jumlah dan tambahkan tanda + atau - berdasarkan jenis transaksi
        if (jenisTransaksi.equals("Pemasukan")) {
            return "+" + String.format("Rp %.2f", jumlah);  // Tanda + untuk Pemasukan
        } else if (jenisTransaksi.equals("Pengeluaran")) {
            return "-" + String.format("Rp %.2f", jumlah);  // Tanda - untuk Pengeluaran
        }
        return String.format("Rp %.2f", jumlah);  // Default formatting if needed
    }
    public interface OnItemDeleteListener {
        void onItemDelete(int position);
    }

    private OnItemDeleteListener deleteListener;

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        this.deleteListener = listener;
    }
    @Override
    public int getItemCount() {
        return transaksiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal, kategori, jumlah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tanggal);
            kategori = itemView.findViewById(R.id.kategori);
            jumlah = itemView.findViewById(R.id.jumlah);
        }
    }


}
