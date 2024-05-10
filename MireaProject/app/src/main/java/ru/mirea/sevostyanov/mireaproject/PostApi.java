package ru.mirea.sevostyanov.mireaproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {
    @GET("/posts?id=1")
    Call<List<Post>> getPosts();
}