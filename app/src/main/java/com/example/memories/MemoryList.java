package com.example.memories;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;


public class MemoryList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Memory> list;
    MemoryListAdapter adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memories_list);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new MemoryListAdapter(this, R.layout.memory_items, list);
        gridView.setAdapter(adapter);

        // get all data from sql
        Cursor cursor = Main2Activity.sqLiteHelper.getData("SELECT * FROM MEMORY");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String location = cursor.getString(1);
            String date = cursor.getString(2);
            String description = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            list.add(new Memory(location,date,description,image, id));
        }
        adapter.notifyDataSetChanged();


    }
}
