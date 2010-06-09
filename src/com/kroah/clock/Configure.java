package com.kroah.clock;

import java.util.ArrayList;

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
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);
		
		// Find the widget id from the intent.
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		// If they gave us an intent without the widget id, get out
		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			finish();
		}

		okButton = (Button)findViewById(R.id.myButton);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d(MODULE, "button clicked");
				Intent result = new Intent();
				result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
				setResult(RESULT_OK, result);
				finish();
			}
		});
		
		
		ListView myListView = (ListView)findViewById(R.id.myListView);

		timezone_list = new ArrayList<String>();
		timezone_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timezone_list);
		myListView.setAdapter(timezone_adapter);

		timezone_list.add(0, "timezone 1");
		timezone_adapter.notifyDataSetChanged();
	}
}
