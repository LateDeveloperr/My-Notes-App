package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    Adapter adapter;
    List<Model> noteslist;
    DatabaseClass databaseClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recycler_view);
        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddNotesActivity.class);
                startActivity(intent);
            }
        });


        noteslist=new ArrayList<>();
        databaseClass=new DatabaseClass(this);
        fetchAllNotesFromDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(this,MainActivity.this,noteslist);
        recyclerView.setAdapter(adapter);
    }

    void fetchAllNotesFromDatabase()
    {
      Cursor cursor = databaseClass.readAllData();
      if (cursor.getCount()==0)
      {
          Toast.makeText(MainActivity.this, "No Data to show", Toast.LENGTH_SHORT).show();
      }
      else
      {
          while (cursor.moveToNext())
          {
              noteslist.add(new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
          }
      }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.searchbar);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search notes here");

        SearchView.OnQueryTextListener listener=new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(listener);


        return super.onCreateOptionsMenu(menu);
    }
}