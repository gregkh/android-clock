package com.kroah.clock;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MedAppWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context,
						   AppWidgetManager appWidgetManager,
						   int[] appWidgetIds) {
		if (appWidgetIds == null) {
			appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MedAppWidget.class));
		}
		
		final int num_ids = appWidgetIds.length;
		for (int i = 0; i < num_ids; i++) {
			int appWidgetId = appWidgetIds[i];
			
			// Create a remote view
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_loading);
			
			// Tie clicking on the button to bring up our configure screen
			Intent intent = new Intent(context, Configure.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			views.setOnClickPendingIntent(R.id.loading, pendingIntent);
			
			// TODO update the UI
			
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
	
}

