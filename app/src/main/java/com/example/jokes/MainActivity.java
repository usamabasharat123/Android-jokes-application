package com.example.jokes;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView jokeTextView;
    private Button fetchJokeButton;
    private JokeApiService jokeApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeTextView = findViewById(R.id.jokeTextView);
        fetchJokeButton = findViewById(R.id.fetchJokeButton);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v2.jokeapi.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the API service
        jokeApiService = retrofit.create(JokeApiService.class);

        // Set click listener for the "Fetch Joke" button
        fetchJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchJoke();
            }
        });

    }

    private void fetchJoke() {
        // Make a network request to get a joke
        Call<Joke> call = jokeApiService.getJoke();
        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if (response.isSuccessful()) {
                    Joke joke = response.body();
                    if (joke != null && joke.getSetup() != null && joke.getDelivery() != null) {
                        // Display the joke in the TextView
                        String jokeText = joke.getSetup() + "\n\n" + joke.getDelivery();
                        jokeTextView.setText(jokeText);
                    } else {
                        // If setup or delivery is null, show an error message
                        jokeTextView.setText("Failed to fetch a complete joke. Please try again.");
                    }
                } else {
                    // Handle error
                    jokeTextView.setText("Error fetching joke. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                // Handle failure
                jokeTextView.setText("Failed to fetch joke");
            }
        });

        TextView underlinedTextView = findViewById(R.id.developerTextView);

        // Set underlining programmatically
        underlinedTextView.setPaintFlags(underlinedTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void openLinkedInProfile(View view) {
        String linkedInProfileUrl = "https://linkedin.com/in/subktgeen-babar";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedInProfileUrl));
        startActivity(intent);
    }
}
