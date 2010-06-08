package com.kroah.clock;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Clock extends Activity {
	
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
	        //newGame();
	        return true;
	    case MENU_SETTINGS:
	        //quit();
	        return true;
	    case MENU_ABOUT:
	    	return true;
	    case MENU_EXIT:
	    	finish();
	    	return true;
	    }
	    return false;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}