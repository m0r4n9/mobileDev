package ru.mirea.sevostyanov.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.mirea.sevostyanov.mireaproject.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private static final String PREFS_NAME = "UserPrefs";
    private static final String NAME_KEY = "UserName";
    private static final String AGE_KEY = "UserAge";
    private static final String HOBBY_KEY = "UserHobby";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProfile();

        binding.btnSave.setOnClickListener(v -> saveProfile());
    }

    private void loadProfile() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String name = prefs.getString(NAME_KEY, "");
        int age = prefs.getInt(AGE_KEY, 0);
        String hobby = prefs.getString(HOBBY_KEY, "");

        binding.editTextName.setText(name);
        binding.editTextAge.setText(age > 0 ? String.valueOf(age) : "");
        binding.editTextHobby.setText(hobby);
    }

    private void saveProfile() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String name = binding.editTextName.getText().toString();
        int age = Integer.parseInt(binding.editTextAge.getText().toString());
        String hobby = binding.editTextHobby.getText().toString();

        editor.putString(NAME_KEY, name);
        editor.putInt(AGE_KEY, age);
        editor.putString(HOBBY_KEY, hobby);

        editor.apply();
    }
}
