package com.kroah.clock;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
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
			
			// TODO update the UI
			
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
	
}

