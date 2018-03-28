package com.example.miniapp2;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by emily_000 on 3/26/2018.
 */

public class RecipeAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Recipe> recipeList;
    private LayoutInflater mInflater;

    // constructor
    public RecipeAdapter(Context mContext, ArrayList<Recipe> recipeList){
        this.mContext = mContext;
        this.recipeList = recipeList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int i) {
        return recipeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null){
            view = mInflater.inflate(R.layout.list_item_recipe, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.thumbnail = view.findViewById(R.id.thumnail);
            viewHolder.titleTextView = view.findViewById(R.id.titleTextView);
            viewHolder.servingNumTextView = view.findViewById(R.id.servingNumTextView);
            viewHolder.dietTextView = view.findViewById(R.id.dietTextView);
            viewHolder.prepTextView = view.findViewById(R.id.prepTextView);
            viewHolder.wantToMakeBtn = view.findViewById(R.id.wantToMakeBtn);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ImageView thumbnail = viewHolder.thumbnail;
        TextView titleTextView = viewHolder.titleTextView;
        TextView servingNumTextView = viewHolder.servingNumTextView;
        TextView dietTextView = viewHolder.dietTextView;
        TextView prepTextView = viewHolder.prepTextView;
        Button wantToMakeBtn = viewHolder.wantToMakeBtn;

        final Recipe recipe = (Recipe) getItem(i);

        titleTextView.setText(recipe.title);
        servingNumTextView.setText(Integer.toString(recipe.servings));
        dietTextView.setText("  " + recipe.diet);
        prepTextView.setText(recipe.prep);
        wantToMakeBtn.setText(">");
        Picasso.with(mContext).load(recipe.image).into(thumbnail);

        wantToMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "channel_ID");
                builder.setStyle(new NotificationCompat.BigTextStyle(builder)
                        .bigText("Tap for the recipe")
                        .setBigContentTitle("Let's cook " + recipe.title)
                        .setSummaryText("Cooking Instructions"))
                        .setContentTitle("Title")
                        .setContentText("Summary")
                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .setAutoCancel(true);


                Uri webpage = Uri.parse(recipe.url);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                final PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
                        webIntent, 0);

                builder.setContentIntent(pendingIntent);
                final NotificationManagerCompat nm = NotificationManagerCompat.from(mContext);
                nm.notify((int)System.currentTimeMillis(), builder.build());

            }
        });

        return view;
    }

    private static class ViewHolder{
        public ImageView thumbnail;
        public TextView titleTextView;
        public TextView servingNumTextView;
        public TextView dietTextView;
        public TextView prepTextView;
        public Button wantToMakeBtn;
    }
}
