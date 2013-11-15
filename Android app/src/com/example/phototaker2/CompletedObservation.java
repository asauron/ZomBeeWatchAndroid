package com.example.phototaker2;

import java.util.Vector;

import com.example.phototaker2.db.ZomBeeDBOpenHelper;
import com.example.phototaker2.db.ZomBeeDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;

public class CompletedObservation extends Activity {
	
	
	ZomBeeDataSource datasource;
    private ZomBeeDBOpenHelper database;
    private Long mRowId;
    ListView lv;
    public String dbstuff;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completed_observation);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		  datasource = new ZomBeeDataSource(this);
	      datasource.open();
	      lv = (ListView) findViewById(R.id.list1);
	        populateCompletedList();
	}

	
	//To show only completed observations
	private void populateCompletedList() {
	
		    	Log.d("Completed observation", "In populate list-1");
		    	Cursor note = datasource.fetchAllObservations();
		        startManagingCursor(note);

		        Vector<String> vec = new Vector<String>();
		       
		        
		        while(note.moveToNext()){
		        	Log.d("AAA","AAA");
		        	Integer nextStep = datasource.getNextStepfromName((note.getString(note.getColumnIndexOrThrow(database.COLUMN_NAME))));
		        	TextView tv = new TextView(getApplicationContext());
		        	//Log.d("AAA", note.getString(note.getColumnIndexOrThrow(database.COLUMN_STEP1_ID)));
		        	tv.setText(note.getString(note.getColumnIndexOrThrow(database.COLUMN_NAME)));
		        	dbstuff = tv.getText().toString();
		        	
		        	if(nextStep != 0)
		        		Toast.makeText(this, "No completed observations", Toast.LENGTH_SHORT).show();
		        	else
		        		vec.add(dbstuff);
		        	//vec.add(dbstuff);
		        	}
		        
		        
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, vec);
				lv.setAdapter(adapter);
		
             //on click show the submitted sample's info
				lv.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						
						
						TextView sampleNameTv = (TextView)arg1;
						int observationId = datasource.getObservationIdByName(sampleNameTv.getText().toString());
						// TODO Auto-generated method stub
						Log.d("observation method", (((TextView) arg1).getText().toString()));
						Log.d("Completed observation","id is "+ observationId);
						//retrieve next todo step from db
						Intent intent = new Intent(getApplicationContext(), DetailsCompletedObservations.class);
						Bundle b = new Bundle();
						b.putInt("ObsID",observationId);
						intent.putExtras(b);
						startActivity(intent);

					}

				});

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
		getMenuInflater().inflate(R.menu.completed_observation, menu);
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
