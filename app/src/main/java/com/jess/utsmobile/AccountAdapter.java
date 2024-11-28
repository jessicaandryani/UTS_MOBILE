package com.jess.utsmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<AccountItem> accountItemList;

    public AccountAdapter(List<AccountItem> accountItemList) {
        this.accountItemList = accountItemList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        AccountItem item = accountItemList.get(position);
        holder.icon.setImageResource(item.getIcon());
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return accountItemList.size();
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;

        public AccountViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }
    }
}
