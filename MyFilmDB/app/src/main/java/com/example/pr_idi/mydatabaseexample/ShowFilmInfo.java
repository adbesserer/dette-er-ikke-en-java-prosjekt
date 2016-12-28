package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowFilmInfo extends AppCompatActivity {
    Film film;
    FilmData filmData;
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
        System.out.println("THE  TITLE IS now "+title);
        film=filmData.getfilm(title);
        System.out.println("THE  TITLE IS  "+film.getTitle());
        if(film!=null) {
            System.out.println("DOING STUFF");
            TextView t = (TextView) findViewById(R.id.show_director);
            t.setText(film.getDirector());
            t.setTextColor(Color.rgb(255, 255, 255));
            System.out.println("DOING STUFF");
            t = (TextView) findViewById(R.id.show_title);
            t.setText(film.getTitle());
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
            t = (TextView) findViewById(R.id.show_rating);
            t.setText((Integer.toString(film.getCritics_rate()) + "/10"));
            t.setTextColor(Color.rgb(255, 255, 255));
            t = (TextView) findViewById(R.id.show_staricon);
            t.setText(new String(Character.toChars(0x2605)));
            t.setTextColor(Color.parseColor("#eeba30"));
        }
    }
}
