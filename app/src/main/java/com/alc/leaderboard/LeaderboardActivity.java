package com.alc.leaderboard;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alc.leaderboard.databinding.ActivityLeaderboardBinding;
import com.alc.leaderboard.fragments.LeaderboardFragment;
import com.alc.leaderboard.fragments.LearnerFragment;
import com.google.android.material.tabs.TabLayout;

public class LeaderboardActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 2;
    LeaderboardPagerAdapter adapter;
    ViewPager viewPager;
    ActivityLeaderboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_leaderboard);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        View view = getSupportActionBar().getCustomView();
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener((v) -> {
            startActivity(new Intent(LeaderboardActivity.this,
                    SubmissionActivity.class));
        });
        adapter = new LeaderboardPagerAdapter(getSupportFragmentManager());
        viewPager = binding.pager;
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.setupWithViewPager(viewPager);
    }

    private class LeaderboardPagerAdapter extends FragmentPagerAdapter {

        public LeaderboardPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new LeaderboardFragment();
                case 1:
                    return new LearnerFragment();
                default:
                    return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.leaders);
                case 1:
                    return getString(R.string.skill);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
