package com.example.miniappstarwars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Context mContext;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;
    private TextView seenView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seenView = findViewById(R.id.seen_view);

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
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

                // put details inside the intent
                detailIntent.putExtra("title",selectedMovie.title);
                detailIntent.putExtra("poster",selectedMovie.poster);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("position",i);

                startActivityForResult(detailIntent, 1);;

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                String radioString = data.getStringExtra("radioString");
                int position = data.getIntExtra("position",-1);
                Movie clickedMovie = movies.get(position);
                clickedMovie.hasSeen = radioString;

            }
        }
    }
}
