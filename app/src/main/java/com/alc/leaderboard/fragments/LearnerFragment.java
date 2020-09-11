package com.alc.leaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alc.leaderboard.adapters.LeaderAdapter;
import com.alc.leaderboard.containers.TopHours;
import com.alc.leaderboard.databinding.FragmentLearnerBinding;
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
public class LearnerFragment extends Fragment {

    private FragmentLearnerBinding learnerBinding;
    private List<TopHours> topHours;

    public LearnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        topHours = new ArrayList<>();
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        LeaderAdapter leaderAdapter = new LeaderAdapter(topHours, getContext());
        GadsAPI gadsAPI = Controller.getClient().create(GadsAPI.class);
        Call<List<TopHours>> listCall = gadsAPI.getTopHours();
        listCall.enqueue(new Callback<List<TopHours>>() {
            @Override
            public void onResponse(Call<List<TopHours>> call, Response<List<TopHours>> response) {
                topHours = response.body();
                leaderAdapter.setTopHours(topHours);
            }

            @Override
            public void onFailure(Call<List<TopHours>> call, Throwable t) {

            }
        });
        // Inflate the layout for this fragment
        learnerBinding = FragmentLearnerBinding.inflate(inflater);
        RecyclerView recyclerView = learnerBinding.recyclerview2;
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(leaderAdapter);
        return learnerBinding.getRoot();
    }
}
