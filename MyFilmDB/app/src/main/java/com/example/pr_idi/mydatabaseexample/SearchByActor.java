package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class SearchByActor extends AppCompatActivity {

    private ListView lv;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SearchView searchView;
    private FilmData filmData;
    private Toast noResult;
    private boolean shouldCleanToast = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_actor);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search by actor");
        lv = (ListView)findViewById(R.id.listview);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navview);

        final Intent filmList = new Intent(this, MainActivity.class);
        filmList.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        final Intent openDetail = new Intent(this, SortedByYear.class);
        openDetail.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_seccion_1:
                                startActivity(filmList);
                                break;
                            case R.id.menu_seccion_2:
                                startActivity(openDetail);
                                break;
                            case R.id.menu_seccion_3:
                                break;
                            default:
                                break;
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );

        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setQueryHint("Write the actor's name");
        filmData = new FilmData(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //System.out.println(query);
                filmData.open();
                showFilms(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (shouldCleanToast) {
                    noResult.cancel();
                }
                return false;
            }
        });
    }

    public void showFilms(String query) {
        List<Film> values = filmData.getFilmsByActor(query);
        filmData.close();
        if (values.size() == 0) {
            shouldCleanToast = true;
            noResult = Toast.makeText(getApplicationContext(), "None of your films include the actor " +query+ ". Please, try again.", Toast.LENGTH_LONG);
            noResult.setGravity(Gravity.CENTER, 0, 20);
            noResult.show();
        }
        ArrayAdapter<Film> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
            default:
                break;
        }

        return true;
    }
}
