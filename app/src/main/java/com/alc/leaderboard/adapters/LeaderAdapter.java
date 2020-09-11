package com.alc.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alc.leaderboard.R;
import com.alc.leaderboard.containers.TopHours;
import com.alc.leaderboard.databinding.HoursBinding;

import java.util.List;


public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.BindingHolder> {

    private List<TopHours> hours;
    private Context mContext;

    public LeaderAdapter(List<TopHours> hours, Context mContext) {
        this.hours = hours;
        this.mContext = mContext;
    }

    public void setTopHours(List<TopHours> newHours) {
        hours.clear();
        hours.addAll(newHours);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HoursBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.hours,
                parent, false);
        return new BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        TopHours topHours = hours.get(position);
        holder.hBinding.imageView2.setImageResource(R.drawable.skill_iq);
        holder.hBinding.textView2.setText(topHours.getName());
        holder.hBinding.textView.setText(String.format("%s skill IQ Score, %s",
                String.valueOf(topHours.getHours()), topHours.getCountry()));
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        HoursBinding hBinding;

        public BindingHolder(View itemView) {
            super(itemView);
            hBinding = DataBindingUtil.bind(itemView);
        }
    }
}
