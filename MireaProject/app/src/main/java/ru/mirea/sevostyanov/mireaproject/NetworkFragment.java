package ru.mirea.sevostyanov.mireaproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkFragment extends Fragment {

    private TextView infoTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_network, container, false);
        infoTextView = rootView.findViewById(R.id.infoTextView);
        fetchData();
        return rootView;
    }

    public void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi getPosts = retrofit.create(PostApi.class);

        getPosts.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> cats = response.body();
                    if (cats != null && !cats.isEmpty()) {
                        Post cat = cats.get(0);
                        infoTextView.setText(cat.body);
                    } else {
                        // Выводим сообщение о том, что данных нет
                        System.out.println("Нет данных о котах");
                    }
                } else {
                    // Выводим сообщение об ошибке
                    System.out.println("Ошибка при получении данных: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("Ошибка при выполнении запроса: " + t.getMessage());
            }
        });
    }
}