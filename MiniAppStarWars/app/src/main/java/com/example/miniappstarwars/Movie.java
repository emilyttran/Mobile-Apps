package com.example.miniappstarwars;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by emily_000 on 2/12/2018.
 */

public class Movie {

    public String title;
    public int episodeNum;
    public String actors;
    public String description;
    public String poster;
    public String url;

    // constructor that reads file and puts info into an ArrayList
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){

        ArrayList<Movie> moviesList = new ArrayList<>();
        try {
            // load the json file into a String
            String jsonString = loadJsonFromAsset("movies.JSON", context);

            // get JSON object from string
            JSONObject json = new JSONObject(jsonString);

            // get the JSON array using the tags
            JSONArray movies = json.getJSONArray("movies");

            // go through each movie and create a Movie object
            for(int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();

                movie.title = movies.getJSONObject(i).getString("title");
                movie.episodeNum = movies.getJSONObject(i).getInt("episode_number");
                movie.actors = movies.getJSONObject(i).getString("main_characters");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.poster = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");

                // add the object to arraylist
                moviesList.add(movie);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        return moviesList;
    }

    // helper method that loads from any Json file and returns the json string
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
