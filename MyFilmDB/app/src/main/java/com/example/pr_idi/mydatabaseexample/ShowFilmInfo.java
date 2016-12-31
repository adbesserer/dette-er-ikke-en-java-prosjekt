package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class ShowFilmInfo extends AppCompatActivity {
    Film film;
    FilmData filmData;
    RatingBar ratingBar;
    float oldRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_film_info);
        String title;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                title = null;
            } else {
                title = extras.getString("title");
            }
        }else{
            title = (String) savedInstanceState.getSerializable("title");
        }

        filmData = new FilmData(this);
        filmData.open();
        film=filmData.getfilm(title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        if(film!=null) {
            TextView t = (TextView) findViewById(R.id.show_director);
            t.setText(film.getDirector());
            t.setTextColor(Color.rgb(255, 255, 255));

            t = (TextView) findViewById(R.id.show_country);
            t.setText(film.getCountry());
            t.setTextColor(Color.rgb(255, 255, 255));

            t = (TextView) findViewById(R.id.show_year);
            t.setText(Integer.toString(film.getYear()));
            t.setTextColor(Color.rgb(255, 255, 255));

            t = (TextView) findViewById(R.id.show_protagonist);
            t.setText(film.getProtagonist());
            t.setTextColor(Color.rgb(255, 255, 255));

            ratingBar = (RatingBar)findViewById(R.id.ratingBar);
            oldRating = (float)(film.getCritics_rate()/2.);
            ratingBar.setRating(oldRating);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    filmData.open();
                    filmData.modifyRating(film,rating*2);
                }
            });
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }

        return true;
    }
}
