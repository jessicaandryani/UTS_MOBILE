package com.jess.utsmobile;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    private RecyclerView recyclerView;
    private AccountAdapter accountAdapter;
    private List<AccountItem> accountItemList;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Setup GridLayoutManager untuk RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3)); // 3 kolom

        // Inisialisasi list dan adapter
        accountItemList = new ArrayList<>();
        accountItemList.add(new AccountItem("Tentang", R.drawable.info));
        accountItemList.add(new AccountItem("Pengaturan", R.drawable.setting));
        accountItemList.add(new AccountItem("Pesan Masuk", R.drawable.kontak));
        accountItemList.add(new AccountItem("Pengingat", R.drawable.alarm));
        accountItemList.add(new AccountItem("Beri Komentar", R.drawable.like));
        accountItemList.add(new AccountItem("Kartu", R.drawable.card));

        accountAdapter = new AccountAdapter(accountItemList);
        recyclerView.setAdapter(accountAdapter);

        return view;
    }
}
