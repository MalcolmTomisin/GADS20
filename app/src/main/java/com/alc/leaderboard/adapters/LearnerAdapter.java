package com.alc.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alc.leaderboard.R;
import com.alc.leaderboard.containers.TopLearners;
import com.alc.leaderboard.databinding.LearnerBinding;

import java.util.ArrayList;
import java.util.List;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.BindingHolder> {

    private List<TopLearners> learners = new ArrayList<>();
    private Context mContext;

    public LearnerAdapter(List<TopLearners> learners, Context mContext) {
        this.learners = learners;
        this.mContext = mContext;
    }

    public void setLearners(List<TopLearners> topLearners) {
        learners.clear();
        learners.addAll(topLearners);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearnerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.learner, parent, false);
        return new BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        holder.mBinding.textView4.setText(learners.get(position).getName());
        holder.mBinding.textView3.setText(String.format("%s learning hours, %s", String.valueOf(learners.get(position).getScore()),
                learners.get(position).getCountry()));
        holder.mBinding.imageView4.setImageDrawable(mContext.getDrawable(R.drawable.top_learner));
    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        LearnerBinding mBinding;

        public BindingHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
