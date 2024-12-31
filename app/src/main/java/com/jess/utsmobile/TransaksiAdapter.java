package com.jess.utsmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {

    private ArrayList<TransaksiModel> transaksiList;
    private OnItemDeleteListener deleteListener;

    public TransaksiAdapter(ArrayList<TransaksiModel> transaksiList) {
        this.transaksiList = transaksiList;
    }

    public interface OnItemDeleteListener {
        void onItemDelete(int position);
    }

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        this.deleteListener = listener;
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

        holder.tanggal.setText(transaksi.getTanggal());
        holder.kategori.setText(transaksi.getKategori());
        holder.jumlah.setText(formatJumlah(transaksi.getJumlah(), transaksi.getJenis()));

        holder.itemView.setOnClickListener(v -> {
            new android.app.AlertDialog.Builder(v.getContext())
                    .setTitle("Hapus Transaksi")
                    .setMessage("Apakah Anda yakin ingin menghapus transaksi ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        if (deleteListener != null) {
                            deleteListener.onItemDelete(position);
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        });
    }

    private String formatJumlah(double jumlah, String jenisTransaksi) {
        if (jenisTransaksi.equals("Pemasukan")) {
            return "+Rp " + String.format(Locale.getDefault(), "%,.0f", jumlah);
        } else if (jenisTransaksi.equals("Pengeluaran")) {
            return "-Rp " + String.format(Locale.getDefault(), "%,.0f", jumlah);
        }
        return "Rp " + String.format(Locale.getDefault(), "%,.0f", jumlah);
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
