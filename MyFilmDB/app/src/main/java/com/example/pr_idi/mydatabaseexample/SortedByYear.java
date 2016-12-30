package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class SortedByYear extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorted_by_year);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Films Detail");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider);
        ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        FilmData filmData= new FilmData(this);
        filmData.open();
        List<Film> values = filmData.getFilmsByYear();
        filmData.close();
        mAdapter = new MyAdapter(values);
        mRecyclerView.setAdapter(mAdapter);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navview);

        final Intent filmList = new Intent(this, MainActivity.class);
        filmList.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        final Intent openSBA = new Intent(this, SearchByActor.class);
        openSBA.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_seccion_1:
                                startActivity(filmList);
                                break;
                            case R.id.menu_seccion_2:
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
    protected void onResume() {
        FilmData filmData= new FilmData(this);
        filmData.open();
        List<Film> values = filmData.getFilmsByYear();
        filmData.close();
        mAdapter = new MyAdapter(values);
        mRecyclerView.setAdapter(mAdapter);
        super.onResume();
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
                Intent intent2 = new Intent(this, DeleteFromSorted.class);
                startActivity(intent2);
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
            default:
                break;
        }

        return true;
    }
}
