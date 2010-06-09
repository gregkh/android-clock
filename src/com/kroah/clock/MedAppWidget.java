package com.kroah.clock;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class MedAppWidget extends AppWidgetProvider {

	
	@Override
	public void onUpdate(Context context,
						   AppWidgetManager appWidgetManager,
						   int[] appWidgetIds) {
		if (appWidgetIds == null) {
			appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MedAppWidget.class));
		}
		
//		UpdateService.requestUpdate(appWidgetIds);
		context.startService(new Intent(context, UpdateService.class));
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
			
//			// Tie clicking on the button to bring up our configure screen
//			Intent intent = new Intent(context, Configure.class);
//			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//			views.setOnClickPendingIntent(R.id.loading, pendingIntent);
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

