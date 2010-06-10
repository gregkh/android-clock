package com.kroah.clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;


public class UpdateService extends Service implements Runnable {
	private static final String MODULE = "GREGKHUpdateService";
	
	private static final long UPDATE_INTERVAL = DateUtils.MINUTE_IN_MILLIS;
	
	// Our lock for the list of times.
	private static Object lock = new Object();
	private static boolean thread_running;
	
	public static final String ACTION_UPDATE_ALL = "com.kroah.clock.UPDATE_ALL";
	
	@Override
	public void onStart(Intent intent, int startId) {
		RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.widget);
		Date date = new Date();
		DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());

		updateViews.setTextViewText(R.id.loading, format.format(date));

		ComponentName thisWidget = new ComponentName(this, MedAppWidget.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		manager.updateAppWidget(thisWidget, updateViews);
		synchronized (lock) {
			if (!thread_running) {
				thread_running = true;
				new Thread(this).start();
			}
		}
	}
	
	public void run() {
		Log.d(MODULE, "Processing thread started");
		
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
//		ContentResolver resolve = getContentResolver();

		RemoteViews updateViews = null;
		
		updateViews = MedAppWidget.buildUpdate(this);

		// Draw the updated time
		
//		RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.widget_loading);
		Date date = new Date();
		DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());

		updateViews.setTextViewText(R.id.loading, format.format(date));


		ComponentName thisWidget = new ComponentName(this, MedAppWidget.class);
		manager.updateAppWidget(thisWidget, updateViews);
		
		// Schedule our next update
		Time time = new Time();
		time.set(System.currentTimeMillis() + UPDATE_INTERVAL);
		time.second = 0;
		long nextUpdate = time.toMillis(false);
		
		Intent updateIntent = new Intent(ACTION_UPDATE_ALL);
		updateIntent.setClass(this, UpdateService.class);
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, updateIntent, 0);
		
		AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, nextUpdate, pendingIntent);
	
		thread_running = false;
		stopSelf();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
