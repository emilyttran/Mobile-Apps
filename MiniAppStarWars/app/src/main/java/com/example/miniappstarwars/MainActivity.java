package com.example.miniappstarwars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
        movies = Movie.getMoviesFromFile("movies.json", mContext);

        adapter = new MovieAdapter(mContext, movies);
        listView = findViewById(R.id.movie_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // get selected movie
                Movie selectedMovie = movies.get(i);

                // create Intent package
                Intent detailIntent = new Intent(mContext,MovieDetailActivity.class);

                // put details inside the intent
                detailIntent.putExtra("title",selectedMovie.title);
                detailIntent.putExtra("poster",selectedMovie.poster);
                detailIntent.putExtra("description", selectedMovie.description);

                startActivity(detailIntent);;

            }
        });
    }
}
