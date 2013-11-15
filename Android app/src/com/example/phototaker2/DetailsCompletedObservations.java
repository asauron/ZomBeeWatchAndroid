package com.example.phototaker2;

import com.example.phototaker2.db.ZomBeeDBOpenHelper;
import com.example.phototaker2.db.ZomBeeDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class DetailsCompletedObservations extends Activity {

	
	ZomBeeDataSource datasource;
    private ZomBeeDBOpenHelper database;

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		// Show the Up button in the action bar.
		setupActionBar();
		
		 datasource = new ZomBeeDataSource(this);
	     datasource.open();
	      
	      String name = "";
	      String method = "";
	      String pupae ="";
	      String flies = "";
	      String date= "";
	      
	    		  
	      
	      Bundle bdn = getIntent().getExtras();
			int id = bdn.getInt("ObsID");
			Log.d("ObsID ","id is " +id);    
	      Cursor c = datasource.getDataForSubmission(id);
	      
	      if (c.moveToNext()){
				
				
			 name = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_NAME));
			 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_NAME)));
			 method = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_METHOD));
			 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_METHOD)));
			 pupae = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_PUPAE));
			 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_PUPAE)));
			 flies = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_FLIES));
			 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_FLIES)));
			 date = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_DATE1));
			 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_DATE1)));
			}
		
		TextView sampleName = (TextView)findViewById(R.id.samplename);
		sampleName.setText(name);
		
		TextView Date1 = (TextView)findViewById(R.id.date1);
		Date1.setText(date);
		
		TextView Pupae = (TextView)findViewById(R.id.pupaeno);
		Pupae.setText(pupae);
		
		TextView Method = (TextView)findViewById(R.id.method);
		Method.setText(method);
		
		TextView Flies = (TextView)findViewById(R.id.zombeeno);
		Flies.setText(flies);
		
	      
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details_completed_observations, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
