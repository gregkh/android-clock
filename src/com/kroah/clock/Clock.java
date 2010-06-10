package com.kroah.clock;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;



public class Clock extends Activity {
	public static final String MODULE = "GREGKHMainScreen";
	
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

	static final private int MENU_ABOUT = 0;
	static final private int MENU_EXIT = 1;
	static final private int MENU_CONFIG = 2;

	/* Creates the menu items */
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, MENU_ABOUT, 0, "About").setIcon(R.drawable.menu_about);
	    menu.add(0, MENU_EXIT, 0, "Exit").setIcon(R.drawable.menu_close);
	    menu.add(0, MENU_CONFIG, 0, "Configure");
	    return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ABOUT:
			about();
			return true;
		case MENU_EXIT:
			finish();
			return true;
		case MENU_CONFIG:
			Intent intent = new Intent(this, Configure.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	private Button okButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		okButton = (Button)findViewById(R.id.myButton);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d(MODULE, "button clicked");
				finish();
			}
		});
	}
}
