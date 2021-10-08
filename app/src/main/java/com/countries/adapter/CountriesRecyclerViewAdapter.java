package com.countries.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.countries.model.Country;
import com.cuntries.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountriesRecyclerViewAdapter extends RecyclerView.Adapter<CountriesRecyclerViewAdapter.ViewHolder> {
    List<Country> countriesData;

    public interface OnItemClickListener {
        void onItemClick(Country item);
    }

    private final OnItemClickListener itemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item, parent, false);
        return new ViewHolder(item);
    }

    public CountriesRecyclerViewAdapter(List<Country> countriesData, OnItemClickListener itemClickListener) {
        this.countriesData = countriesData;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (countriesData != null) {
            Country ct = countriesData.get(position);
            holder.name.setText(ct.getName());
            holder.capital.setText(ct.getCapital());
            holder.engName.setText(ct.getAlpha2Code());
            holder.attachClickListener(ct);
        }
    }

    @Override
    public int getItemCount() {
        if (countriesData == null) {
            return 0;
        }
        return countriesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView engName;
        TextView capital;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            engName = itemView.findViewById(R.id.tv_eng_name);
            capital = itemView.findViewById(R.id.tv_capital);
        }

        private void attachClickListener(final Country country) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(country);
                }
            });
        }
    }

    public void sort(Comparator comparator) {
        Collections.sort(countriesData, comparator);
        notifyDataSetChanged();
    }
}
