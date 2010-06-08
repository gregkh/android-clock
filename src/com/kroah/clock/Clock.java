package com.kroah.clock;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;



public class Clock extends Activity {
	
	final void about() {
//		Dialog d = new Dialog(Clock.this);
//		Window w = d.getWindow();
//		w.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//		d.setTitle("Dialog title");
//		d.setContentView(R.layout.dialog_view);
//		d.show();
		
		Context context = Clock.this;
		String title = "it is pitch black";
		String message = "you are likely to trip and fall in a hole.";
		String button1String = "Go Back";
		String button2String = "Move Forward";
		
		AlertDialog.Builder ad = new AlertDialog.Builder(context);
		ad.setTitle(title);
		ad.setMessage(message);
		ad.setPositiveButton(button1String,
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
		});
		ad.setNegativeButton(button2String,
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
	        //newGame();
	        return true;
	    case MENU_SETTINGS:
	        //quit();
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
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}