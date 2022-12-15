package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcherwildhuntguidbook.R;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderHolder> {
    @NonNull
    @Override
    public HeaderAdapter.HeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
