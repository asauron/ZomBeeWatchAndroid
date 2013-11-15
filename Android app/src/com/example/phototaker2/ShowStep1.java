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

public class ShowStep1 extends Activity {
	
	ZomBeeDataSource datasource;
    private ZomBeeDBOpenHelper database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_step1);
		// Show the Up button in the action bar.
		setupActionBar();
		Log.i("to be submitted", "in show step 1");
		datasource = new ZomBeeDataSource(this);
	    datasource.open();
	    
	      String name = "";
	      String method = "";
	      String date= "";
	      String bees ="";
	      
	      Bundle bdn = getIntent().getExtras();
		  String samplename = bdn.getString("samplename");
		  Log.i("the ","name is " + samplename);
		  
		  int number = datasource.getPreviousStep1fromName(samplename);
		  Log.d("from step 2 is","num "+number);
		  
		  Cursor c = datasource.fetchStep1Note(number);
	
		  
		  
		 if (c.moveToNext()){
				
				Log.i("cursor","is moving");
				 name = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_NAME));
				 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_NAME)));
				 method = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_METHOD));
				 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_METHOD)));
				 date = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_DATE1));
				 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_DATE1)));
				 bees = c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_NUMBERBEES));
				 Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_NUMBERBEES)));
			}
		  
		  TextView name1 = (TextView)findViewById(R.id.name1);
			name1.setText(name);
			
			TextView datestep1 = (TextView)findViewById(R.id.step1date);
			datestep1.setText(date);
			
			
			TextView Method = (TextView)findViewById(R.id.methodofcollection);
			Method.setText(method);
			
			TextView Bees = (TextView)findViewById(R.id.noofbees);
			Bees.setText(bees);
			
		  
		  
		  
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
		getMenuInflater().inflate(R.menu.show_step1, menu);
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
