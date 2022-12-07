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
    View.OnClickListener itemOnClickListener;


    /*public interface ItemOnClickListener {
        public abstract void onClick (View view);
    }*/

    ItemAdapter(ArrayList<Item> itemList, View.OnClickListener itemOnClickListener) {
        this.itemList = itemList;
        this.itemOnClickListener = itemOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weapon_card, parent, false);
        return new ViewHolder(view);
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

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.weapon_image);
            name = view.findViewById(R.id.weapon_name);
            tier = view.findViewById(R.id.weapon_tier);
            weight = view.findViewById(R.id.weapon_weight);
        }
    }
}
