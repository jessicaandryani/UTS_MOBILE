package com.jess.utsmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaporanFragment extends Fragment {

    private PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laporan, container, false);

        // Inisialisasi PieChart
        pieChart = view.findViewById(R.id.pieChart_view);

        // Inisialisasi dan tampilkan pie chart
        initPieChart();
        fetchChartData();

        return view;
    }

    private void initPieChart() {
        // Menggunakan persentase sebagai nilai
        pieChart.setUsePercentValues(true);

        // Menghapus label deskripsi di sudut kiri bawah
        pieChart.getDescription().setEnabled(false);

        // Mengaktifkan rotasi chart
        pieChart.setRotationEnabled(true);
        // Menambahkan gesekan saat memutar chart
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        // Mengatur sudut awal dari sisi kanan
        pieChart.setRotationAngle(0);

        // Menyoroti entri saat diketuk
        pieChart.setHighlightPerTapEnabled(true);
        // Menambahkan animasi agar entri muncul dari 0 derajat
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        // Mengatur warna lubang di tengah
        pieChart.setHoleColor(Color.parseColor("#000000"));
    }

    private void fetchChartData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your-api-base-url.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        String apiKey = getString(R.string.google_api_key);
        Call<GoogleApiResponse> call = apiService.getChartData(apiKey);

        call.enqueue(new Callback<GoogleApiResponse>() {
            @Override
            public void onResponse(Call<GoogleApiResponse> call, Response<GoogleApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    showPieChart(response.body());
                }
            }

            @Override
            public void onFailure(Call<GoogleApiResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void showPieChart(GoogleApiResponse response) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        // Inisialisasi data dari response
        for (GoogleApiResponse.DataItem item : response.data) {
            pieEntries.add(new PieEntry(item.value, item.category));
        }

        // Inisialisasi warna untuk entri
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));
        colors.add(Color.parseColor("#476567"));
        colors.add(Color.parseColor("#890567"));
        colors.add(Color.parseColor("#a35567"));
        colors.add(Color.parseColor("#ff5f67"));
        colors.add(Color.parseColor("#3ca567"));

        // Mengumpulkan entri dengan label
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        // Mengatur ukuran teks nilai
        pieDataSet.setValueTextSize(12f);
        // Menyediakan daftar warna untuk mewarnai entri berbeda
        pieDataSet.setColors(colors);
        // Mengelompokkan data set dari entri ke chart
        PieData pieData = new PieData(pieDataSet);
        // Menampilkan nilai entri, default true jika tidak diatur
        pieData.setDrawValues(true);

        // Mengubah nilai menjadi persentase
        pieData.setValueFormatter(new PercentFormatter());

        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}