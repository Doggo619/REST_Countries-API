package com.base.restcountries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<CountryEntity> countryEntityList;
    public void setCountries(List<CountryEntity> countries) {
        this.countryEntityList = countries;
        notifyDataSetChanged();
    }
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        if (countryEntityList != null) {
            CountryEntity currentCountry = countryEntityList.get(position);
            holder.countryName.setText(currentCountry.getName());
            holder.capitalName.setText(currentCountry.getCapital());
            String flagUrl = currentCountry.getFlags();
            if (flagUrl != null) {
                Picasso.get()
                        .load(currentCountry.getFlags())
                        .error(R.drawable.ic_launcher_foreground)
                        .into(holder.flagImage);
            }

        }
    }

    @Override
    public int getItemCount() {
        if (countryEntityList != null)
            return countryEntityList.size();
        else return 0;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        private TextView countryName;
        private TextView capitalName;
        private ImageView flagImage;

        public CountryViewHolder(View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.tv_country);
            capitalName = itemView.findViewById(R.id.tv_capital);
            flagImage = itemView.findViewById(R.id.iv_flag);
        }
    }
}
