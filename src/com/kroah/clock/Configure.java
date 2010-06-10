package com.kroah.clock;

import java.util.ArrayList;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public class Configure extends Activity {

	public static final String MODULE = "GREGKHConfigureScreen";
	public static int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	private Button okButton;

	// Display a simple "About" dialog
	final void about() {
		AlertDialog.Builder ad = new AlertDialog.Builder(Configure.this);
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

	static final private int MENU_SETTINGS = 1;
	static final private int MENU_ABOUT = 2;
	static final private int MENU_EXIT = 3;

	/* Creates the menu items */
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, MENU_SETTINGS, 0, "Settings").setIcon(R.drawable.menu_preferences);
	    menu.add(0, MENU_ABOUT, 0, "About").setIcon(R.drawable.menu_about);
	    menu.add(0, MENU_EXIT, 0, "Exit").setIcon(R.drawable.menu_close);
	    return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_SETTINGS:
			//settings();
			return true;
		case MENU_ABOUT:
			about();
			return true;
		case MENU_EXIT:
			Intent result = new Intent();
			result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
			setResult(RESULT_OK, result);
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
		Log.d(MODULE, "onCreate:enter");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);

		// Find the widget id from the intent.
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			Log.d(MODULE, "onCreate:extras != null");
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		// If they gave us an intent without the widget id, get out
		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			Log.d(MODULE, "onCreatea:mAppWidgetId == INVALID");
			finish();
		}

		okButton = (Button)findViewById(R.id.myButton);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d(MODULE, "ok button clicked");
				Intent result = new Intent();
				result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
				setResult(RESULT_OK, result);
				Log.d(MODULE, "ok button finishing");
				finish();
			}
		});


	
		ListView list_view = (ListView)findViewById(R.id.time_zone_list);

		timezone_list = new ArrayList<String>();
		timezone_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timezone_list);
		list_view.setAdapter(timezone_adapter);

//		TimeZone zone = new TimeZone();
		String[] timezones = TimeZone.getAvailableIDs();
		for (String s: timezones)
			timezone_list.add(s);
	
		
		//timezone_list.add(0, "timezone 1");
		timezone_adapter.notifyDataSetChanged();

		Log.d(MODULE, "onCreate:exit");
	}
}
