package com.example.pr_idi.mydatabaseexample;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.pr_idi.mydatabaseexample.R.id.film_title;

/**
 * Created by adb on 12/25/16.
 */



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Film> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {

            super(v);
            mView = v;
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Film> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_attributes, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Film film = mDataset.get(position);
        TextView t = (TextView) holder.mView.findViewById(R.id.film_director);
        t.setText("Director: "+film.getDirector());
        t=(TextView) holder.mView.findViewById(R.id.film_title);
        t.setText(film.getTitle()+" ");
        t.setTextColor(Color.rgb(255,255,255));
        t=(TextView) holder.mView.findViewById(R.id.film_country);
        t.setText("Country: "+film.getCountry());
        t=(TextView) holder.mView.findViewById(R.id.film_year);
        t.setText("("+Integer.toString(film.getYear())+")");
        t.setTextColor(Color.rgb(255,255,255));
        t=(TextView) holder.mView.findViewById(R.id.film_star);
        t.setText("Starring: "+film.getProtagonist());
        t=(TextView) holder.mView.findViewById(R.id.film_rating);
        t.setText((Integer.toString(film.getCritics_rate())+"/10"));
        t=(TextView) holder.mView.findViewById(R.id.star_icon);
        t.setText(new String(Character.toChars(0x2605)));
        t.setTextColor(Color.parseColor("#eeba30"));


    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
