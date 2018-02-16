package com.example.miniapp1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Context mContext;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        movies = Movie.getMoviesFromFile("movies.JSON", mContext);

        adapter = new MovieAdapter(mContext,movies);
        listView = findViewById(R.id.movie_list_view);
        listView.setAdapter(adapter);
    }
}
