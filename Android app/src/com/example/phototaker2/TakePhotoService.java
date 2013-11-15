package com.example.phototaker2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class TakePhotoService extends Service {

	private final IBinder mBinder = new MyBinder();

	@Override
	public void onStart(Intent intent, int startId) {
		// super.onStart(intent, startId);

		Intent dialogIntent = new Intent(getBaseContext(), CycleActivity.class);
		dialogIntent.setAction(Intent.ACTION_VIEW);
		dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		dialogIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		getApplication().startActivity(dialogIntent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public class MyBinder extends Binder {
		TakePhotoService getService() {
			return TakePhotoService.this;
		}
	}

}
