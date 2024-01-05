package com.example.jokes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeApiService {
    @GET("/joke/Any")
    Call<Joke> getJoke();
}

