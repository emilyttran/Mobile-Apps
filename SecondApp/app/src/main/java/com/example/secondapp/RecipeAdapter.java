package com.example.secondapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by emily_000 on 2/7/2018.
 */

// adapter is needed when you want to do any sort of list/table/recycler view
    // gets data and decides where to display in the activity
public class RecipeAdapter extends BaseAdapter{ // must override all methods from BaseAdapter
    // adapter takes the app itself and a list of data to display

    // instance variables
    private Context mContext;
    private ArrayList<Recipe> mRecipeList;
    private LayoutInflater mInflater;

    // constructor
    public RecipeAdapter(Context mContext, ArrayList<Recipe> mRecipeList){

        // initialize instance variables
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // METHODS
        // a list of methods we need to override

    // gives you the number of recipes in the data source
    @Override
    public int getCount(){
        return mRecipeList.size();
    }

    // returns the item at a specific position in the data source
    @Override
    public Object getItem(int position){
        return mRecipeList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    // gets the view and decides what information goes into which view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
            // if yes, you don't need to inflate and finViewbyID again
            // else...
        if(convertView == null){

            // inflate
            convertView = mInflater.inflate(R.layout.list_item_recipe, parent, false);

            // add the views to the holder
            holder = new ViewHolder();

            // views
            holder.titleTextView = convertView.findViewById(R.id.recipe_list_title);
            holder.servingsTextView = convertView.findViewById(R.id.recipe_list_serving);
            holder.thumbnailImageView = convertView.findViewById(R.id.recipe_list_thumb);

            // add the holder to the view for future use (only does the holder process once
            convertView.setTag(holder);
        } else {

            // get the view holder from convertView
            holder = (ViewHolder) convertView.getTag();
        }
        // get relevant subview of the row view (each view of the layout)
            // you don't want to change the actual holder because holder is just a template
        TextView titleTextView = holder.titleTextView;
        TextView servingTextView = holder.servingsTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        // get corresponding recipe for each row
        Recipe recipe = (Recipe) getItem(position);

        // update the row view's textviews and imageview to display the information

        // titleTextView
        titleTextView.setText(recipe.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        titleTextView.setTextSize(18);

        // servingTextView
        servingTextView.setText(recipe.servings + " servings");
        servingTextView.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimaryDark));
        servingTextView.setTextSize(14);

        // imageView
        // use Picasso library to load image from the image URLs
        Picasso.with(mContext).load(recipe.imageURL).into(thumbnailImageView);
        return convertView;
    }

    // viewHolder
        // is used to customize what you want to put into the view
        // it depends on the layout design of your row
        // this will be a private static class you will have to define
    private static class ViewHolder{
        // instance variables
        public TextView titleTextView;
        public TextView servingsTextView;
        public ImageView thumbnailImageView;
    }

    // intent is used to pass information between activities
    // intent is like a package (sender -> receiver)

}
