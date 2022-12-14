package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcherwildhuntguidbook.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private final ArrayList<Item> itemList;
    ItemClickListener itemOnClickListener;


    public interface ItemClickListener {
        void onClick (int position);
    }

    ItemAdapter(ArrayList<Item> itemList, ItemClickListener itemOnClickListener) {
        this.itemList = itemList;
        this.itemOnClickListener = itemOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weapon_card, parent, false);
        return new ViewHolder(view, itemOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.imageView.setImageResource(item.getWeaponResource());
        holder.name.setText(item.getName());
        holder.tier.setText(item.getTier());
        holder.weight.setText(item.getWeight());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView name;
        final TextView tier;
        final TextView weight;

        public ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.weapon_image);
            name = itemView.findViewById(R.id.weapon_name);
            tier = itemView.findViewById(R.id.weapon_tier);
            weight = itemView.findViewById(R.id.weapon_weight);

            itemView.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    int pos = getAbsoluteAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        itemClickListener.onClick(pos);
                    }
                }
            });
        }
    }
}
