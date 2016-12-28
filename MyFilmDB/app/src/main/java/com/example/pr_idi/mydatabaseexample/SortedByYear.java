package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorted_by_year);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

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
            case R.id.action_menu:
                //menu
                break;
            default:
                break;
        }

        return true;
    }
}
