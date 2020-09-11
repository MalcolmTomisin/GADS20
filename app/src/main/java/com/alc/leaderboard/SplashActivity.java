package com.alc.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static long SPLASH = 3000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(this::startNewActivity,
                SPLASH);
    }

    private void startNewActivity() {
        startActivity(new Intent(this, LeaderboardActivity.class));
        finish();
    }
}
