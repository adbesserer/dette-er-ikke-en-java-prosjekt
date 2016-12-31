package com.example.pr_idi.mydatabaseexample;


import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
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
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

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
           filmData.createFilm("Rushmore", "Wes Anderson", 1998, "USA", "Jason Schwartzman", 8);
           filmData.createFilm("Shichinin no samurai", "Akira Kurosawa", 1954, "Japan", "Several", 9);
           filmData.createFilm("There Will Be Blood", "P.T. Anderson", 2007, "USA", "Daniel Day-Lewis", 9);
           filmData.createFilm("Barry Lyndon", "Stanley Kubrick", 1975, "USA", "Ryan O'Neal", 8);
        }
        showfilms();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Films");

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

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navview);

        final Intent openDetails = new Intent(this, SortedByYear.class);
        openDetails.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        final Intent openSBA = new Intent(this, SearchByActor.class);
        openSBA.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_seccion_1:
                                break;
                            case R.id.menu_seccion_2:
                                startActivity(openDetails);
                                break;
                            case R.id.menu_seccion_3:
                                startActivity(openSBA);
                                break;
                            default:
                                break;
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );

    }
    public void showfilms(){
        List<Film> values = filmData.getFilmsByTitle();
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Film> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        ArrayAdapter<Film> adapter1 = new ArrayAdapter<>(this,R.layout.item, R.id.Item_name,values);
        lv.setAdapter(adapter1);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    /*
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_year:
                Intent intent = new Intent(this, SortedByYear.class);
                startActivity(intent);
                break;
        }

    }
    */

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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }

        return true;
    }
}

