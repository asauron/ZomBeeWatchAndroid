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

public class ShowStep2 extends Activity {

	
	ZomBeeDataSource datasource;
    private ZomBeeDBOpenHelper database;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_step2);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Log.i("to be submitted", "in show step 2");
		datasource = new ZomBeeDataSource(this);
	    datasource.open();
	    
	      String name = "";
	      String method = "";
	      String date= "";
	      String bees ="";
	      String pupae ="";
	      String date2 = "";
	      
	      Bundle bdn = getIntent().getExtras();
		  String samplename = bdn.getString("samplename");
		  Log.i("the ","name is " + samplename);
		  
		  int number = datasource.getPreviousStep1fromName(samplename);
		  Log.d("from step 2 is","num "+number);
		  
		  int number2 = datasource.getPreviousStep2fromName(samplename);
		  Log.d("from step 3 is","num "+number2);
		  
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
		  
		  Cursor d = datasource.fetchStep2Note(number2);
		  
		  if(d.moveToNext()){
				Log.i("cursor2","is moving");
				pupae = d.getString(d.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_PUPAE));
				Log.i("Submitting  ",d.getString(d.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_PUPAE)));
				 date2 = d.getString(d.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_DATE2));
				 Log.i("Submitting  ",d.getString(d.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_DATE2)));

			  
		  }
		  
		    TextView name1 = (TextView)findViewById(R.id.name2);
			name1.setText(name);
			
			TextView datestep1 = (TextView)findViewById(R.id.step1date1);
			datestep1.setText(date);
			
			
			TextView Method = (TextView)findViewById(R.id.collection1);
			Method.setText(method);
			
			TextView Bees = (TextView)findViewById(R.id.noofbees1);
			Bees.setText(bees);
			
			TextView Pupaes = (TextView)findViewById(R.id.pupae2);
			Pupaes.setText(pupae);
			
			TextView datestep2 = (TextView)findViewById(R.id.step2date);
			datestep2.setText(date2);
			
		  
		  
		
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
		getMenuInflater().inflate(R.menu.show_step2, menu);
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
