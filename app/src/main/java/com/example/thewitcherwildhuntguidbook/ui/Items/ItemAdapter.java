package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcherwildhuntguidbook.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<Item> doubleItem;

    ItemAdapter(Context context, List<Item> itemList) {
        this.doubleItem = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.weapon_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = doubleItem.get(position);

        holder.imageViewOne.setImageResource(item.getWeaponResourceOne());
        holder.nameOne.setText(item.getNameOne());
        holder.tierOne.setText(item.getTierOne());
        holder.weightOne.setText(item.getWeightOne());

        holder.imageViewTwo.setImageResource(item.getWeaponResourceOne());
        holder.nameTwo.setText(item.getNameOne());
        holder.tierTwo.setText(item.getTierOne());
        holder.weightTwo.setText(item.getWeightOne());
    }

    @Override
    public int getItemCount() {
        return doubleItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageViewOne;
        final TextView nameOne;
        final TextView tierOne;
        final TextView weightOne;

        final ImageView imageViewTwo;
        final TextView nameTwo;
        final TextView tierTwo;
        final TextView weightTwo;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageViewOne = view.findViewById(R.id.image_one);
            nameOne = view.findViewById(R.id.name_one);
            tierOne = view.findViewById(R.id.tier_one);
            weightOne = view.findViewById(R.id.weight_one);

            imageViewTwo = view.findViewById(R.id.image_two);
            nameTwo = view.findViewById(R.id.name_two);
            tierTwo = view.findViewById(R.id.tier_two);
            weightTwo = view.findViewById(R.id.weight_two);
        }
    }
}
