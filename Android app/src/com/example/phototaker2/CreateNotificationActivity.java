package com.example.phototaker2;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class CreateNotificationActivity extends Activity {
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_create_notification);
	  }

	  public void createNotification(View view) {
	    // Prepare intent which is triggered if the
	    // notification is selected
	    Intent intent = new Intent(this, NotificationReceiverActivity.class);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

	    // Build notification
	    // Actions are just fake
	    Notification.Builder builder = new Notification.Builder(this);
	    builder
	    .setSmallIcon(R.drawable.ic_notify)
	    .setContentTitle("Notification Title")
	    .setContentText("Hello There!")
	    .setContentInfo("Content")
	    .setTicker("Android Notification")
	    .setLights(0xFFFF0000, 500, 500) //setLights (int argb, int onMs, int offMs)
	    .setContentIntent(pIntent)
	    .setAutoCancel(true);
	    
	    @SuppressWarnings("deprecation") // For use with API 11.
		Notification noti = builder.getNotification();
	    

	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    // Hide the notification after its selected
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;

	    notificationManager.notify(0, noti);

	  }
} 
