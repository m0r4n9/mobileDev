package ru.mirea.sevostyanov.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Locale;

import ru.mirea.sevostyanov.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView infoTextView = findViewById(R.id.TextForResult);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 11, НОМЕР ПО СПИСКУ: 23, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Волк с уолл стрит");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAveragePairs();
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток No (?) студентом группы No 11 %s номер по списку No 23", numberThread, " БСБО-11-21", -1));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток No " + numberThread);
                        }
                    }
                }).start();
            }
        });
    }

    private void calculateAveragePairs() {
        int totalPairs;
        int totalDays;
        try {
            totalPairs = Integer.parseInt(binding.totalClasses.getText().toString());
            totalDays = Integer.parseInt(binding.totalDays.getText().toString());
        } catch (NumberFormatException e) {
            binding.TextForResult.setText("Пожалуйста, введите числовые значения.");
            return;
        }

        if (totalDays <= 0) {
            binding.TextForResult.setText("Количество учебных дней должно быть больше нуля.");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                final double average = (double) totalPairs / totalDays;

                binding.TextForResult.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.TextForResult.setText(String.format(Locale.getDefault(), "Среднее количество пар в день: %.2f", average));
                    }
                });
            }
        }).start();
    }

}
