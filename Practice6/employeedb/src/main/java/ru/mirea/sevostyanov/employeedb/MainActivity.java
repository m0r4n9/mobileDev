package ru.mirea.sevostyanov.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import ru.mirea.sevostyanov.employeedb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase db = App.getInstance().getDatabase();
        HeroDao employeeDao = db.employeeDao();
        Hero employee = new Hero();
        employee.id = 1;
        employee.name = "Halk";
        employee.power = "Мery strong";
        employeeDao.insert(employee);
        List<Hero> employees = employeeDao.getAll();
        employee = employeeDao.getById(1);
        employee.power = "Monster";
        employeeDao.update(employee);
    }
}