package com.alc.leaderboard.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alc.leaderboard.adapters.LearnerAdapter;
import com.alc.leaderboard.containers.TopLearners;
import com.alc.leaderboard.databinding.FragmentLeaderboardBinding;
import com.alc.leaderboard.repository.Controller;
import com.alc.leaderboard.repository.GadsAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {
    FragmentLeaderboardBinding mBinding;
    RecyclerView view;
    List<TopLearners> learners;
    public LeaderboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        learners = new ArrayList<>();
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        LearnerAdapter learnerAdapter = new LearnerAdapter(learners, getContext());
        GadsAPI gadsAPI = Controller.getClient().create(GadsAPI.class);
        Call<List<TopLearners>> call = gadsAPI.getTopLearners();
        call.enqueue(new Callback<List<TopLearners>>() {
            @Override
            public void onResponse(Call<List<TopLearners>> call, Response<List<TopLearners>> response) {
                learners = response.body();
                learnerAdapter.setLearners(learners);
                Log.d("RAN", "Response =" + response.message());
            }

            @Override
            public void onFailure(Call<List<TopLearners>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
        mBinding = FragmentLeaderboardBinding.inflate(inflater);
        view = mBinding.recyclerview;
        view.setLayoutManager(lm);
        view.setAdapter(learnerAdapter);

        return mBinding.getRoot();
    }
}
