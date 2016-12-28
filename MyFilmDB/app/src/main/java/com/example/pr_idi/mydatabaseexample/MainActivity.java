package com.example.pr_idi.mydatabaseexample;


import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FilmData filmData;
    private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv=(ListView) findViewById(R.id.listview);
        filmData = new FilmData(this);
        filmData.open();
        //if the db is empty, add 4 default films
        //uncomment the next line to refresh the db
        //filmData.deleteALL();
        if(filmData.getAllFilms().size()==0) {
           filmData.createFilm("Barry Lyndon", "Stanley Kubrick", 1975, "USA", "Ryan O'Neal", 8);
           filmData.createFilm("Rushmore", "Wes Anderson", 1998, "USA", "Jason Schwartzman", 8);
           filmData.createFilm("Shichinin no samurai", "Akira Kurosawa", 1954, "Japan", "Several", 9);
           filmData.createFilm("There Will Be Blood", "P.T. Anderson", 2007, "USA", "Daniel Day-Lewis", 9);
        }
        showfilms();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                //on click in listview, show info on clicked film
                Film film = (Film) adapter.getItemAtPosition(position);
                String title= film.getTitle();
                System.out.println("THE TITLE WAS "+title);
                Intent intent = new Intent(getApplicationContext(), ShowFilmInfo.class);
                intent.putExtra("title",String.valueOf(title));
                startActivity(intent);

            }
        });

    }
    public void showfilms(){
        List<Film> values = filmData.getFilmsByTitle();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Film> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_year:
                Intent intent = new Intent(this, SortedByYear.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onResume() {
        filmData.open();
        showfilms();
        super.onResume();
    }

    @Override
    protected void onPause() {
        filmData.close();
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_add:
                Intent intent = new Intent(this, RegisterNewFilm.class);
                startActivity(intent);
                break;
            // action with ID action_settings was selected
            case R.id.action_trash:
                Intent intent2 = new Intent(this, DeleteScreen.class);
                startActivity(intent2);
                break;
            case R.id.action_menu:
                //menu
                break;
            default:
                break;
        }

        return true;
    }
}

