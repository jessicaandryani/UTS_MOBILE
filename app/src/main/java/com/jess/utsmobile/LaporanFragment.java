package com.jess.utsmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LaporanFragment extends Fragment {

    private WebView webView;
    private DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laporan, container, false);

        webView = view.findViewById(R.id.webView);
        db = new DatabaseHelper(getContext());

        // Load Google Charts
        loadGoogleCharts();

        return view;
    }

    private void loadGoogleCharts() {
        double totalPemasukan = db.getTotalPemasukan();
        double totalPengeluaran = db.getTotalPengeluaran();

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        google.charts.load('current', {'packages':['corechart']});\n" +
                "        google.charts.setOnLoadCallback(drawChart);\n" +
                "        function drawChart() {\n" +
                "            var data = google.visualization.arrayToDataTable([\n" +
                "                ['Category', 'Value'],\n" +
                "                ['Pemasukan', " + totalPemasukan + "],\n" +
                "                ['Pengeluaran', " + totalPengeluaran + "]\n" +
                "            ]);\n" +
                "\n" +
                "            var options = {\n" +
                "                title: 'Laporan Keuangan',\n" +
                "                pieHole: 0.4,\n" +
                "            };\n" +
                "\n" +
                "            var chart = new google.visualization.PieChart(document.getElementById('donutchart'));\n" +
                "            chart.draw(data, options);\n" +
                "        }\n" +
                "    </script>\n" +
                "    <style>\n" +
                "        body, html { height: 100%; margin: 0; display: flex; justify-content: center; align-items: center; }\n" +
                "        #donutchart { width: 100%; height: 100%; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"donutchart\"></div>\n" +
                "</body>\n" +
                "</html>";

        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadData(htmlContent, "text/html", "UTF-8");
    }
}