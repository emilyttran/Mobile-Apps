package com.example.miniapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by emily_000 on 2/12/2018.
 */

public class MovieAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    public MovieAdapter(Context mContext, ArrayList<Movie> arrayList)    {

        this.mContext = mContext;
        this.mMovieList = arrayList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMovieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // instantiate a viewholder
        ViewHolder viewHolder;

        // check if view exists
        if(view == null){
            // if not, inflate the view and add a viewholder to it
            view = mInflater.inflate(R.layout.list_item_movie, viewGroup,false);
            viewHolder = new ViewHolder();

            viewHolder.titleTextView = view.findViewById(R.id.title_view);
            viewHolder.descriptionTextView = view.findViewById(R.id.description_view);
            viewHolder.actorsTextView = view.findViewById(R.id.actors_view);
            viewHolder.seenTextView = view.findViewById(R.id.seen_view);
            viewHolder.posterImageView = view.findViewById(R.id.imageView);

            view.setTag(viewHolder);
        } else {
            // if view already exists, get the view holder
            viewHolder = (ViewHolder) view.getTag();
        }

        // use viewholder and get relevant views
        TextView titleTextView = viewHolder.titleTextView;
        TextView descriptionTextView = viewHolder.descriptionTextView;
        TextView actorsTextView = viewHolder.actorsTextView;
        TextView seenTextView = viewHolder.seenTextView;
        ImageView posterImageView = viewHolder.posterImageView;

        // get the movie at the position
        Movie movie = (Movie) getItem(i);

        // update the views to display correct information
        titleTextView.setText(movie.title);
        descriptionTextView.setText(movie.description);
        actorsTextView.setText(movie.actors);
        seenTextView.setText("hi");
        Picasso.with(mContext).load(movie.poster).into(posterImageView);
        return view;

    }

    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView actorsTextView;
        public TextView seenTextView;
        public ImageView posterImageView;
    }
}
