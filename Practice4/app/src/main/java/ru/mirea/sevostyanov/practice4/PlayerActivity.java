package ru.mirea.sevostyanov.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import ru.mirea.sevostyanov.practice4.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {
    private ActivityPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.playButton.setOnClickListener(v -> Log.d("PlayerActivity", "Play button clicked"));
        binding.previousButton.setOnClickListener(v -> Log.d("PlayerActivity", "Previous button clicked"));
        binding.nextButton.setOnClickListener(v -> Log.d("PlayerActivity", "Next button clicked"));
        binding.favoriteButton.setOnClickListener(v -> Log.d("PlayerActivity", "Favorite button clicked"));
        binding.moreActions.setOnClickListener(v -> Log.d("PlayerActivity", "More actions clicked"));
    }
}
