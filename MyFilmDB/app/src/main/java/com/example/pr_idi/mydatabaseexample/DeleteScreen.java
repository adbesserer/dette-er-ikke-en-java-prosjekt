package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

public class DeleteScreen extends AppCompatActivity {
    private FilmData filmData;
    private ListView lv;
    private Set<String> selected_titles = new ArraySet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_screen);
        lv=(ListView) findViewById(R.id.listview);
        filmData = new FilmData(this);
        filmData.open();
        //if the db is empty, add 4 default films
        //uncomment the next line to refresh the db
        //filmData.deleteALL();
        showfilms();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Film value = (Film) adapter.getItemAtPosition(position);
                String title = value.getTitle();
                if(selected_titles.contains(title)) selected_titles.remove(title);
                else selected_titles.add(title);
            }
        });

    }
    public void showfilms(){
        List<Film> values = filmData.getFilmsByTitle();
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Film> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, values);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);
    }
    public void onClickdel(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
            Button btn = (Button) findViewById(R.id.btn_delete);
            btn.setBackgroundColor(Color.parseColor("#559e83"));
                btn.setText("Deleting...");

                Iterator<String> it = selected_titles.iterator();
            while(it.hasNext()){
                filmData.deleteFilmbytitle(it.next().trim());
            }
                finish();
                break;
            case R.id.btn_cancelDel:
                Button btn2 = (Button) findViewById(R.id.btn_cancelDel);
                btn2.setBackgroundColor(Color.parseColor("#559e83"));
                finish();
                break;
        }

    }
    // Will be called via the onClick attribute
    // of the buttons in main.xml
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

}