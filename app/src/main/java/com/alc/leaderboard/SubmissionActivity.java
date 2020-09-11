package com.alc.leaderboard;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alc.leaderboard.databinding.ActivitySubmissionBinding;
import com.alc.leaderboard.repository.Controller;
import com.alc.leaderboard.repository.GadsAPI;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    ActivitySubmissionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submission);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar_submission);

        binding.button2.setOnClickListener((v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            builder.setView(layoutInflater.inflate(R.layout.dialog_action, null));
            AlertDialog dialog = builder.create();
            Button button = dialog.findViewById(R.id.button3);
            button.setOnClickListener((b) -> {
                submit();
            });
            dialog.show();
        });

    }

    private void submit() {
        GadsAPI gadsAPI = Controller.getClient().create(GadsAPI.class);
        String firstName = binding.editText.getText().toString();
        String lastName = binding.editText2.getText().toString();
        String email = binding.editText3.getText().toString();
        String link = binding.editText4.getText().toString();
        Call<Void> call = gadsAPI.submit(firstName, lastName, email, link);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
                if (response.isSuccessful()) {
                    builder.setIcon(R.drawable.ic_icons_checkmark);
                    builder.setMessage("Submission Successful");
                    Log.d("PASS", "Submitted");
                } else {
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setMessage("Submission not Successful");
                }
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage("Submission not Successful");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Log.d("FAIL", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
