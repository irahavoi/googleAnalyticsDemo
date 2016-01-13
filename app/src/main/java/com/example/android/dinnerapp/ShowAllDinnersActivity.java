package com.example.android.dinnerapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowAllDinnersActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_dinners);

        Dinner dinner = new Dinner();
        String [] allDinners = dinner.getAllDinners(this);

        //Create array adapter:
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.show_dinner_in_row, R.id.textview_dinner_row, allDinners);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        //Do something when a list item is clicked.
        super.onListItemClick(l, v, position, id);

        String value = (String) getListView().getItemAtPosition(position);

        Toast.makeText(this, "selected dinner: " + value, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, OrderDinnerActivity.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all_dinners, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
