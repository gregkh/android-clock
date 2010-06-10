package com.kroah.clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MedAppWidget extends AppWidgetProvider {

	public static final String MODULE = "GREGKHWidget";

	@Override
	public void onUpdate(Context context,
						   AppWidgetManager appWidgetManager,
						   int[] appWidgetIds) {
		Log.d(MODULE, "onUpdate:enter");
		if (appWidgetIds == null) {
			appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MedAppWidget.class));
		}

		// Tie clicking on the button to bring up our configure screen
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		if (views == null)
			Log.d(MODULE, "buildUpdate:views == null");
		Intent intent = new Intent(context, Configure.class);
		if (intent == null)
			Log.d(MODULE, "buildUpdate:intent == null");
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, Configure.mAppWidgetId);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		if (pendingIntent == null)
			Log.d(MODULE, "buildUpdate:pendingIntent == null");
		views.setOnClickPendingIntent(R.id.time, pendingIntent);

//		UpdateService.requestUpdate(appWidgetIds);
		context.startService(new Intent(context, UpdateService.class));
		Log.d(MODULE, "onUpdate:exit");
	}

	public static RemoteViews buildUpdate(Context context) {

		Log.d(MODULE, "buildUpdate:enter");
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		if (views == null)
			Log.d(MODULE, "buildUpdate:views == null");

//		ContentResolver resolver = context.getContentResolver();
//		Resources res = context.getResources();
//		Cursor cursor = null;

		// Tie clicking on the button to bring up our configure screen
		Intent intent = new Intent(context, Configure.class);
		if (intent == null)
			Log.d(MODULE, "buildUpdate:intent == null");
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, Configure.mAppWidgetId);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		if (pendingIntent == null)
			Log.d(MODULE, "buildUpdate:pendingIntent == null");
		views.setOnClickPendingIntent(R.id.widget, pendingIntent);

//		Intent result = new Intent();
//		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);

		/* Update the display */
		Date date = new Date();
		DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT, Locale.getDefault());
		views.setTextViewText(R.id.time, format.format(date));
		
		SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy");
		views.setTextViewText(R.id.date, f.format(date));

		
		
		Log.d(MODULE, "buildUpdate:exit");
		return views;

	}

//		final int num_ids = appWidgetIds.length;
//		for (int i = 0; i < num_ids; i++) {
//
//			int appWidgetId = appWidgetIds[i];
//
//			Timer timer = new Timer();
//			timer.scheduleAtFixedRate(new myTime(context, appWidgetManager), 1, 10000);
//
//			// Create a remote view
//			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_loading);

//
//			// set the time
////			DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());
////			views.setTextViewText(R.id.loading, format.format(new Date()));
//			// TODO update the UI
//
//			appWidgetManager.updateAppWidget(appWidgetId, views);
//		}
//	}

}

