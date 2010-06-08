package com.kroah.clock;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;



public class Clock extends Activity {
	
	// Display a simple "About" dialog
	final void about() {
		AlertDialog.Builder ad = new AlertDialog.Builder(Clock.this);
		ad.setTitle(getString(R.string.about_title));
		ad.setMessage(getString(R.string.about));
		ad.setPositiveButton(getString(R.string.ok),
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// nothing
					}
		});
		ad.setCancelable(true);
		ad.setOnCancelListener(new OnCancelListener() {
					public void onCancel(DialogInterface Dialog) {
						// nothing
					}
		});
		ad.show();
	}
	
	static final private int MENU_ADD = 0;
	static final private int MENU_SETTINGS = 1;
	static final private int MENU_ABOUT = 2;
	static final private int MENU_EXIT = 3;
	
	/* Creates the menu items */
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_ADD, 0, "Add").setIcon(R.drawable.menu_add);
	    menu.add(0, MENU_SETTINGS, 0, "Settings").setIcon(R.drawable.menu_preferences);
	    menu.add(0, MENU_ABOUT, 0, "About").setIcon(R.drawable.menu_about);
	    menu.add(0, MENU_EXIT, 0, "Exit").setIcon(R.drawable.menu_close);
	    return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case MENU_ADD:
	        timezone_list.add(0, "timezone foo");
	        timezone_adapter.notifyDataSetChanged();
	        return true;
	    case MENU_SETTINGS:
	        //settings();
	        return true;
	    case MENU_ABOUT:
	    	about();
	    	return true;
	    case MENU_EXIT:
	    	finish();
	    	return true;
	    }
	    return false;
	}
	
    private ArrayList<String> timezone_list;
    private ArrayAdapter<String> timezone_adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView myListView = (ListView)findViewById(R.id.myListView);
        
        timezone_list = new ArrayList<String>();
        timezone_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timezone_list);
        myListView.setAdapter(timezone_adapter);

        timezone_list.add(0, "timezone 1");
        timezone_list.add(0, "timezone 2");
        timezone_list.add(0, "timezone 3");
        timezone_list.add(0, "timezone 4");
        timezone_list.add(0, "timezone 5");
        timezone_list.add(0, "timezone 6");
        timezone_list.add(0, "timezone 7");
        timezone_list.add(0, "timezone 8");
        timezone_list.add(0, "timezone 9");
        timezone_list.add(0, "timezone10");
        timezone_list.add(0, "timezone22");
        timezone_adapter.notifyDataSetChanged();
    }
}