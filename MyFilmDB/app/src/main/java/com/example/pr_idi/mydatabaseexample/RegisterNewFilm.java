package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import static java.lang.Math.round;

public class RegisterNewFilm extends AppCompatActivity {
    private FilmData fd;
    private String title,dir,country,prot;
    private int year,rating;
    private boolean not_filled=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_new_film);
    }
    private void display_error(){
        System.out.println("displaying error");
        if(year<1896 || year>2017) {
            Toast fail = Toast.makeText(getApplicationContext(), "Please, enter a valid year", Toast.LENGTH_LONG);
            fail.setGravity(Gravity.CENTER, 0, 20);
            fail.show();
        }
        else {
            Toast fail = Toast.makeText(getApplicationContext(), "Please, fill in all the blanks", Toast.LENGTH_LONG);
            fail.setGravity(Gravity.CENTER, 0, 20);
            fail.show();
        }
    }
    public void onClick2(View view) {
        switch (view.getId()) {
            case R.id.btn_accept:
                    fd = new FilmData(this);
                      Button btn = (Button) findViewById(R.id.btn_accept);
                    fd.open();
                    EditText et = (EditText) findViewById(R.id.title_field);
                    title = et.getText().toString();
                    et = (EditText) findViewById(R.id.director_field);
                    dir = et.getText().toString();
                    et = (EditText) findViewById(R.id.country_field);
                    country = et.getText().toString();
                    et = (EditText) findViewById(R.id.protagonist_field);
                    prot = et.getText().toString();
                    et = (EditText) findViewById(R.id.year_field);
                    if(et.getText().toString().trim().length()!=0)
                        year = Integer.parseInt(et.getText().toString());
                    else year=0;
                    RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
                    rating = (int)round(rb.getRating() * 2.);
                    System.out.println(title + dir + country +prot + year +rating);
                    if (title.trim().length() == 0 || dir.trim().length() == 0 || country.trim().length() == 0 || prot.trim().length() == 0
                            || year < 1896 || year > 2017) {
                        display_error();
                    } else {
                        btn.setBackgroundColor(Color.parseColor("#559e83"));
                        btn.setText("Film Added!");
                        fd.createFilm(title, dir, year, country, prot, rating);

                        finish();
                    }
                break;
            case R.id.btn_cancel:
                Button btn2 = (Button) findViewById(R.id.btn_cancel);
                btn2.setBackgroundColor(Color.parseColor("#559e83"));
                finish();
                break;

        }
    }
}
