package com.example.pr_idi.mydatabaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_screen);
        TextView t = (TextView) findViewById(R.id.textview_about);
        t.setText("This app was developed by Alejandro Domínguez and Aleix Balletbó" +
                " for a college project in the fall semester of 2016-2017 at the Barcelona School of Informatics" +
                "(FIB).\n\n" +
                "Our project is a simple and intuitive app that holds a database for" +
                " your favourite movies, offering you complete control and easy modification." +"\n\n" +
                "This product is free software: you can redistribute it and/or modify it under the terms of the GNU " +
                "General Public License ad published by the Free Software Foundation, either version 3," +
                 " or any later version.\nSee http://www.gnu.org/licenses for more information." );
    }
}
