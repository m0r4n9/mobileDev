package ru.mirea.sevostyanov.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

import ru.mirea.sevostyanov.internalfilestorage.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "russ_event.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.saveButton.setOnClickListener(v -> SaveData(binding.getRoot()));
    }

    public void SaveData(View view) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(binding.editText.getText().toString().getBytes());
            outputStream.close();
            showToast("Файл сохранен");
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Error saving file");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}